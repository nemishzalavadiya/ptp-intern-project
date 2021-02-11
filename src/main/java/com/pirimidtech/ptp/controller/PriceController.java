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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/stock/{id}/prices")
    public ResponseEntity<List<StockPrice>> getStockPrice(@PathVariable("id") UUID stockId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockPrice> stockPriceList = priceService.getStockPrice(stockId, page, size);
        return stockPriceList.size() != 0 ? ResponseEntity.ok().body(stockPriceList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/mutualfund/{id}/prices")
    public ResponseEntity<List<MutualFundPrice>> getMutualFundPrice(@PathVariable("id") UUID mutualFundId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<MutualFundPrice> mutualFundPriceList = priceService.getMutualFundPrice(mutualFundId, page, size);
        return mutualFundPriceList.size() != 0 ? ResponseEntity.ok().body(mutualFundPriceList) : ResponseEntity.notFound().build();
    }
}
