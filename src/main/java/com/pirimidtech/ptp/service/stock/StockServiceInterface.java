package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.DTO.SelectedStocksFilter;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface StockServiceInterface {
    Page<StockDetail> getAllStockDetails(Pageable paging);

    void addStock(StockDetail stockDetail);

    Optional<StockDetail> getStockDetailById(UUID id);

    Optional<StockStatistic> getStockStatsById(UUID id);

    void addStockStats(StockStatistic stockStatistic);

    Optional<StockStatistic> getStockStatisticByAssetId(UUID id);

    Page<StockStatistic> getStockFilterResults(SelectedStocksFilter selectedStocksFilter, Pageable paging);
}
