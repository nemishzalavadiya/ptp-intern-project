package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockPriceRepository extends JpaRepository<StockPrice, UUID> {
}
