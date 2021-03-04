package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface MutualFundStatisticRepository extends JpaRepository<MutualFundStatistic, UUID>, QuerydslPredicateExecutor<MutualFundStatistic> {
    Optional<MutualFundStatistic> findByMutualFundDetail_AssetDetail_id(UUID assetId);
}
