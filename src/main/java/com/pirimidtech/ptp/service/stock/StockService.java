package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.DTO.StocksFilterRequest;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.entity.QStockStatistic;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.repository.StockStatisticRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements StockServiceInterface {
    @Autowired
    private StockDetailRepository stockDetailRepository;
    @Autowired
    private StockStatisticRepository stockStatisticRepository;

    @Override
    public Page<StockDetail> getAllStockDetails(Pageable paging) {
        return stockDetailRepository.findAll(paging);
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
    public Optional<StockStatistic> getStockStatisticByAssetId(UUID assetId) {
        return stockStatisticRepository.findByStockDetail_AssetDetail_id(assetId);
    }

    public Page<StockStatistic> getStockFilterResults(StocksFilterRequest stocksFilterRequest, Pageable paging) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QStockStatistic qStockStatistic = QStockStatistic.stockStatistic;
        if (stocksFilterRequest != null) {
            if (stocksFilterRequest.getMarketCapLowerLimit() != null) {
                booleanBuilder.and(qStockStatistic.marketCap.goe(stocksFilterRequest.getMarketCapLowerLimit()));
            }
            if (stocksFilterRequest.getMarketCapUpperLimit() != null) {
                booleanBuilder.and(qStockStatistic.marketCap.loe(stocksFilterRequest.getMarketCapUpperLimit()));
            }
        }
        return stockStatisticRepository.findAll(booleanBuilder, paging);
    }
}
