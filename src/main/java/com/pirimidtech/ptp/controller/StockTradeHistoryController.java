package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/history")
public class StockTradeHistoryController {

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;


    @PostMapping("/")
    public ResponseEntity<Void> addToStockTradeHistory(@RequestBody StockTradeHistory stockTradeHistory)
    {
        try {
            stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public List<StockTradeHistory> getStockTradeHistory(@PathVariable("userId") UUID userId )
    {
        List<StockTradeHistory> stockTradeHistoryList = new ArrayList<>();
        try {
            stockTradeHistoryList = stockTradeHistoryService.getStockTradeHistory(userId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeHistoryList;
    }
    @GetMapping("/trade/{tradeId}")
    public StockTradeHistory getStockTradeByTradeId(@PathVariable("tradeId") UUID tradeId)
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