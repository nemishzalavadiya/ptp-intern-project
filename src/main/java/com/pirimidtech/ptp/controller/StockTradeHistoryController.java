package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class StockTradeHistoryController {

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;


    @PostMapping("/stock/trade-history")
    public ResponseEntity<Void> addToStockTradeHistory(@RequestBody StockTradeHistory stockTradeHistory)
    {
        try {
            stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stock/trade-history/user/{id}")
    public List<StockTradeHistory> getStockTradeHistory(@PathVariable("id") UUID userId )
    {
        List<StockTradeHistory> stockTradeHistoryList = new ArrayList<>();
        try {
            stockTradeHistoryList = stockTradeHistoryService.getStockTradeHistory(userId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeHistoryList;
    }
    @GetMapping("/stock/trade-history/{id}")
    public StockTradeHistory getStockTradeByTradeId(@PathVariable("id") UUID tradeId)
    {
        StockTradeHistory stockTradeHistory = new StockTradeHistory();
        try {
            stockTradeHistory = stockTradeHistoryService.getStockTradeByTradeId(tradeId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeHistory;
    }


}