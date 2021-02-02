package com.pirimidtech.ptp.controller;


import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/history")
public class StockTradeHistoryController {

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;

    @PostMapping("/")
    public ResponseEntity addToStockTradeHistory(@RequestBody StockTradeHistory stockTradeHistory)
    {
        stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public List<StockTradeHistory> getStockTradeHistory(@PathVariable("userId") UUID userId )
    {
        return stockTradeHistoryService.getStockTradeHistory(userId);
    }
    @GetMapping("/trade/{tradeId}")
    public StockTradeHistory getStockTradeByTradeId(@PathVariable("tradeId") UUID tradeId)
    {
        return stockTradeHistoryService.getStockTradeByTradeId(tradeId);
    }


}
