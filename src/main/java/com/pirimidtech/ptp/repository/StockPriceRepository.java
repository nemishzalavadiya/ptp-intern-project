package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockPriceRepository extends JpaRepository<StockPrice, UUID> {
    Page<StockPrice> findAllByStockDetailIdOrderByTimestampDesc(UUID stockId, Pageable pageable);

}
