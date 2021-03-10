package com.pirimidtech.ptp.service.kyc;

import com.pirimidtech.ptp.DTO.KYCDetailDTO;

import java.io.IOException;
import java.util.UUID;

public interface KYCServiceInterface {
    void uploadKYCDetail(KYCDetailDTO kycDetailDTO,UUID userId) throws IOException;

    boolean isKycVerified(UUID userId);
}
