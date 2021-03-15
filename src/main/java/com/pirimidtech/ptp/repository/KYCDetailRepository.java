package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.KYCDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KYCDetailRepository extends JpaRepository<KYCDetail, UUID> {
    Optional<KYCDetail> findByUserId(UUID userId);
}
