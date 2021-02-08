package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.StockTradeHistory;

import java.util.List;
import java.util.UUID;

public interface StockTradeHistoryServiceInterface {
    List<StockTradeHistory> getStockTradeHistory(UUID userId,int pageNo,int pageSize);
    void addToStockTradeHistory(StockTradeHistory stockTradeHistory);
    StockTradeHistory getStockTradeByTradeId(UUID tradeId);

}
