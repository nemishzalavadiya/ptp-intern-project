package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface StockStatisticRepository extends JpaRepository<StockStatistic, UUID>, QuerydslPredicateExecutor<StockStatistic> {
    Optional<StockStatistic> findByStockDetail_AssetDetail_id(UUID assetId);
}
