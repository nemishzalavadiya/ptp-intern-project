package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockServiceInterface {
    List<StockDetail> getAllStockDetails();

    void addStock(StockDetail stockDetail);

    Optional<StockDetail> getStockDetailById(UUID id);

    Optional<StockStatistic> getStockStatsById(UUID id);

    void addStockStats(StockStatistic stockStatistic);
}
