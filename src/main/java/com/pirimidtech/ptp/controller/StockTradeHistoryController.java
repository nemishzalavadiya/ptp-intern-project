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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class StockTradeHistoryController {

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;


    @PostMapping("/stock/trade-history")
    public ResponseEntity<UUID> addToStockTradeHistory(@RequestBody StockTradeHistory stockTradeHistory) {
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            stockTradeHistory.setId(uuid);
            stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().body(uuid);
    }

    @GetMapping("/stock/trade-history/user/{id}")
    public ResponseEntity<List<StockTradeHistory>> getStockTradeHistory(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockTradeHistory> stockTradeHistoryList = new ArrayList<>();
        try {
            stockTradeHistoryList = stockTradeHistoryService.getStockTradeHistory(userId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeHistoryList.size() != 0 ? ResponseEntity.ok().body(stockTradeHistoryList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stock/trade-history/{id}")
    public ResponseEntity<StockTradeHistory> getStockTradeByTradeId(@PathVariable("id") UUID tradeId) {
        StockTradeHistory stockTradeHistory;
        try {
            stockTradeHistory = stockTradeHistoryService.getStockTradeByTradeId(tradeId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeHistory != null ? ResponseEntity.ok().body(stockTradeHistory) : ResponseEntity.notFound().build();
    }


}