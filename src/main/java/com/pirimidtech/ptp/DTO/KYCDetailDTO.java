package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KYCDetailDTO {
    private String panNumber;
    private String occupation;
    private int annualIncome;
    private String motherName;
    private String fatherName;

    private MultipartFile pan;
    private MultipartFile signature;
    private MultipartFile profile;
}
