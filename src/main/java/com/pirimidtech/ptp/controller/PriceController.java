package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/stock/{id}/prices")
    public ResponseEntity<List<StockPrice>> getStockPrice(@PathVariable("id") UUID stockId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<StockPrice> stockPriceList = priceService.getStockPrice(stockId, page, size);
        return ResponseEntity.ok().body(stockPriceList);
    }

    @GetMapping("/mutualfund/{id}/prices")
    public ResponseEntity<List<MutualFundPrice>> getMutualFundPrice(@PathVariable("id") UUID mutualFundId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<MutualFundPrice> mutualFundPriceList = priceService.getMutualFundPrice(mutualFundId, page, size);
        return ResponseEntity.ok().body(mutualFundPriceList);
    }

}
