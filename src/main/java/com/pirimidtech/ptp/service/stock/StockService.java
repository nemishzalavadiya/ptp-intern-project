package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.repository.StockStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements StockServiceInterface {
    @Autowired
    private StockDetailRepository stockDetailRepository;
    @Autowired
    private StockStatisticRepository stockStatisticRepository;

    @Override
    public List<StockDetail> getAllStockDetails() {
        List<StockDetail> stocks = new ArrayList<>();
        stockDetailRepository.findAll().forEach(stocks::add);
        return stocks;
    }

    @Override
    public void addStock(StockDetail stockDetail) {
        stockDetailRepository.save(stockDetail);
    }

    @Override
    public Optional<StockDetail> getStockDetailById(UUID id) {
        return stockDetailRepository.findById(id);
    }

    @Override
    public Optional<StockStatistic> getStockStatsById(UUID id) {
        return stockStatisticRepository.findById(id);
    }

    @Override
    public void addStockStats(StockStatistic stockStatistic) {
        stockStatisticRepository.save(stockStatistic);
    }

    @Override
    public Optional<StockDetail> getStockDetailByCompanyId(UUID id) {
        return stockDetailRepository.findByAssetDetailId(id);
    }
}
