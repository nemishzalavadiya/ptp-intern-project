package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockStatisticRepository extends JpaRepository<StockStatistic, UUID> {
}
