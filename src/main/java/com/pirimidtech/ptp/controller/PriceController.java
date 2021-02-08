package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.price.PriceService;
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
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/stock/prices")
    public ResponseEntity<UUID> addToStockPrice(@RequestBody StockPrice stockPrice) {
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            stockPrice.setId(uuid);
            priceService.addToStockPrice(stockPrice);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().body(uuid);
    }

    @GetMapping("/stock/{id}/prices")
    public ResponseEntity<List<StockPrice>> getStockPrice(@PathVariable("id") UUID stockId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockPrice> stockPriceList = new ArrayList<>();
        try {
            stockPriceList = priceService.getStockPrice(stockId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockPriceList.size() != 0 ? ResponseEntity.ok().body(stockPriceList) : ResponseEntity.notFound().build();
    }

    @PostMapping("/mutualfund/prices")
    public ResponseEntity<UUID> addToMutualFundPrice(@RequestBody MutualFundPrice mutualFundPrice) {
        UUID uuid;
        try {
            uuid  = UUID.randomUUID();
            mutualFundPrice.setId(uuid);
            priceService.addToMutualFundPrice(mutualFundPrice);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mutualfund/{id}/prices")
    public ResponseEntity<List<MutualFundPrice>> getMutualFundPrice(@PathVariable("id") UUID mutualFundId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<MutualFundPrice> mutualFundPriceList = new ArrayList<>();
        try {
            mutualFundPriceList = priceService.getMutualFundPrice(mutualFundId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundPriceList.size() != 0 ? ResponseEntity.ok().body(mutualFundPriceList) : ResponseEntity.notFound().build();
    }

}
