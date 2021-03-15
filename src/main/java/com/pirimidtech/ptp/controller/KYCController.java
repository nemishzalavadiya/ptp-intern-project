package com.pirimidtech.ptp.controller;


import com.pirimidtech.ptp.DTO.KYCDetailDTO;
import com.pirimidtech.ptp.service.kyc.KYCService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@RestController
public class KYCController {

    @Autowired
    private KYCService kycService;

    @Autowired
    private RequestUtil requestUtil;

    @PostMapping(value = "/kyc")
    ResponseEntity<String> uploadKYC(HttpServletRequest httpServletRequest, KYCDetailDTO kycDetail) throws IOException {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        kycService.uploadKYCDetail(kycDetail, userId);
        return ResponseEntity.ok("Kyc verified");
    }

    @GetMapping(value = "/kyc")
    ResponseEntity<Boolean> getKyc(HttpServletRequest httpServletRequest) throws IOException {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        boolean isKycVerified = kycService.isKycVerified(userId);
        return ResponseEntity.ok().body(isKycVerified);
    }


}
