package com.pirimidtech.ptp.service.kyc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pirimidtech.ptp.DTO.KYCDetailDTO;
import com.pirimidtech.ptp.DTO.KYCResponsePOJO;
import com.pirimidtech.ptp.entity.KYCDetail;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.exception.KYCVerificationFailedException;
import com.pirimidtech.ptp.repository.KYCDetailRepository;
import com.pirimidtech.ptp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class KYCService implements KYCServiceInterface {


    @Value("${KycURL}")
    String serverUrl;
    @Autowired
    private KYCDetailRepository kycDetailRepository;
    @Autowired
    private UserService userService;
    @Value("${UPLOADED_FOLDER}")
    private String UPLOADED_FOLDER;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private void createDirectories() {
        File root = new File(UPLOADED_FOLDER);
        if (!root.exists()) {
            File panDirectory = new File(UPLOADED_FOLDER + "Pan");
            File profileDirectory = new File(UPLOADED_FOLDER + "Profile");
            File signatureDirectory = new File(UPLOADED_FOLDER + "Signature");
            root.mkdir();
            panDirectory.mkdir();
            profileDirectory.mkdir();
            signatureDirectory.mkdir();
        }

    }

    @Override
    public void uploadKYCDetail(KYCDetailDTO kycDetailDTO, UUID userId) throws IOException {
        createDirectories();
        MultipartFile panFile = kycDetailDTO.getPan();
        MultipartFile profile = kycDetailDTO.getProfile();
        MultipartFile sign = kycDetailDTO.getSignature();
        Path panPath = Paths.get(UPLOADED_FOLDER + "Pan//" + userId.toString() + panFile.getOriginalFilename());
        Path profilePath = Paths.get(UPLOADED_FOLDER + "Profile//" + userId.toString() + profile.getOriginalFilename());
        Path signaturePath = Paths.get(UPLOADED_FOLDER + "Signature//" + userId.toString() + sign.getOriginalFilename());

        Files.write(panPath, panFile.getBytes());
        Files.write(profilePath, profile.getBytes());
        if (!verifyKYCDetails(panPath, profilePath)) {
            Files.delete(panPath);
            Files.delete(profilePath);
            throw new KYCVerificationFailedException("Enter valid Images");
        } else {
            Files.write(signaturePath, sign.getBytes());
            User user = userService.getUserById(userId).get();
            user.setKycVerified(true);
            KYCDetail kycDetail = new KYCDetail(null, kycDetailDTO.getPanNumber(), kycDetailDTO.getOccupation(), kycDetailDTO.getAnnualIncome(), kycDetailDTO.getMotherName(), kycDetailDTO.getFatherName(), panFile.getOriginalFilename(), sign.getOriginalFilename(), profile.getOriginalFilename(), user);
            kycDetailRepository.save(kycDetail);
        }
    }

    private boolean verifyKYCDetails(Path panFile, Path profile) throws JsonProcessingException {


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FileSystemResource pan = new FileSystemResource(panFile);
        FileSystemResource photo = new FileSystemResource(profile);
        body.add("id_img", pan);
        body.add("photo", photo);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<KYCResponsePOJO> response = restTemplate.postForEntity(serverUrl, requestEntity, KYCResponsePOJO.class);
        KYCResponsePOJO root = response.getBody();
        if (root.getMessage() == null) {
            throw new KYCVerificationFailedException("Enter Valid Images");
        }
        double cosineDistance = root.getCosineDistance();
        double distance = root.getDistance();
        if (distance == -1) {
            throw new KYCVerificationFailedException(root.getMessage());
        }
        return cosineDistance < 0.5 & distance < 1;

    }

    @Override
    public boolean isKycVerified(UUID userId) {
        Optional<KYCDetail> kycDetailOptional = kycDetailRepository.findByUserId(userId);
        if (kycDetailOptional.isPresent()) {
            return true;
        }
        return false;
    }
}
