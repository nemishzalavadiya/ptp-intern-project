package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface StockPriceRepository extends JpaRepository<StockPrice, UUID> {
    List<StockPrice> findAllByStockId(UUID stockId);
}
