package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.DTO.SelectedStocksFilter;
import com.pirimidtech.ptp.entity.QStockStatistic;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.repository.StockStatisticRepository;
import com.pirimidtech.ptp.service.datagenerator.DataGenerator;
import com.pirimidtech.ptp.service.datagenerator.Stock;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService implements StockServiceInterface {
    @Autowired
    private StockDetailRepository stockDetailRepository;
    @Autowired
    private StockStatisticRepository stockStatisticRepository;
    @Autowired
    private DataGenerator dataGenerator;

    @Override
    public Page<StockDetail> getAllStockDetails(Pageable paging) {
        return stockDetailRepository.findAll(paging);
    }
    @Override
    public Page<StockStatistic> getAllStockStatistics(Pageable paging) {
        return stockStatisticRepository.findAll(paging);
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

    public boolean isClosePriceInRange(Stock item, SelectedStocksFilter selectedStocksFilter) {
        return (item.getClose() >= selectedStocksFilter.getClosingRange().getMinimum())
                && (item.getClose() <= selectedStocksFilter.getClosingRange().getMaximum());
    }

    public Page<StockStatistic> filterClosePrice(BooleanBuilder booleanBuilder, SelectedStocksFilter selectedStocksFilter, Pageable paging) {
        List<StockStatistic> filteredList = stockStatisticRepository
                .findAll(booleanBuilder, paging)
                .getContent()
                .stream()
                .filter(stats -> {
                    UUID companyId = stockDetailRepository.findById(stats.getId()).get().getAssetDetail().getId();
                    return dataGenerator.getGeneratedStockList().stream()
                            .anyMatch(item ->
                                    item.getCompanyId().equals(companyId) && isClosePriceInRange(item, selectedStocksFilter)
                            );
                }).collect(Collectors.toList());
        return new PageImpl<>(filteredList, paging, filteredList.size());
    }

    public Page<StockStatistic> getStockFilterResults(SelectedStocksFilter selectedStocksFilter, Pageable paging) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QStockStatistic qStockStatistic = QStockStatistic.stockStatistic;
        if (selectedStocksFilter != null) {
            if (selectedStocksFilter.getMarketCapRange().getMinimum() != null) {
                booleanBuilder.and(qStockStatistic.marketCap.goe(selectedStocksFilter.getMarketCapRange().getMinimum()));
            }
            if (selectedStocksFilter.getMarketCapRange().getMaximum() != null) {
                booleanBuilder.and(qStockStatistic.marketCap.loe(selectedStocksFilter.getMarketCapRange().getMaximum()));
            }
        }
        return filterClosePrice(booleanBuilder, selectedStocksFilter, paging);
    }
}
