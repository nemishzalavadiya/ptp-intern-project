package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockTradeHistoryService implements StockTradeHistoryServiceInterface {

    @Autowired
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    @Override
    public void addToStockTradeHistory(StockTradeHistory stockTradeHistory) {
        stockTradeHistoryRepository.save(stockTradeHistory);
    }
}
