package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MutualFundDetailRepository extends JpaRepository<MutualFundDetail, UUID> {
    Optional<MutualFundDetail> findByAssetDetailId(UUID companyId);
}
