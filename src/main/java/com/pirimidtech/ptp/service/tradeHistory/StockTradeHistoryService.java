package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class StockTradeHistoryService implements StockTradeHistoryServiceInterface{

    @Autowired
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    @Override
    public List<StockTradeHistory> getStockTradeHistory(UUID userId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StockTradeHistory> pageResult=stockTradeHistoryRepository.findAllByUserId(userId,pageable);
        return pageResult.toList();
    }

    @Override
    public void addToStockTradeHistory(StockTradeHistory stockTradeHistory) {
            stockTradeHistoryRepository.save(stockTradeHistory);
    }

    @Override
    public StockTradeHistory getStockTradeByTradeId(UUID tradeId) {
        Optional<StockTradeHistory> stockTradeHistory=stockTradeHistoryRepository.findById(tradeId);
        return stockTradeHistory.isPresent()?stockTradeHistory.get():null;
    }
}
