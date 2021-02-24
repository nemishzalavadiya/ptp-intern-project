package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MutualFundStatisticRepository extends JpaRepository<MutualFundStatistic, UUID> {
    Optional<MutualFundStatistic> findByMutualFundDetail_AssetDetail_id(UUID assetId);
    Page<MutualFundStatistic> findByRisk(Pageable paging, String risk);
    Page<MutualFundStatistic> findBySipAllowed(Pageable paging, boolean sipAllowed);
    Page<MutualFundStatistic> findByFundSizeBetween(Pageable paging, float sizeOpen,float sizeClose);
}
