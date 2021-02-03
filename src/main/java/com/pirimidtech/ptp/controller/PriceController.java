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
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/stock/prices")
    public ResponseEntity<Void> addToStockPrice(@RequestBody StockPrice stockPrice)
    {
        try {
            priceService.addToStockPrice(stockPrice);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stock/{id}/prices")
    public List<StockPrice> getStockPrice(@PathVariable("id")UUID stockId)
    {
        List<StockPrice> stockPriceList = new ArrayList<>();
        try {
            stockPriceList = priceService.getStockPrice(stockId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockPriceList;
    }

    @PostMapping("/mutualfund/prices")
    public ResponseEntity<Void> addToMutualFundPrice(@RequestBody MutualFundPrice mutualFundPrice)
    {
        try {
            priceService.addToMutualFundPrice(mutualFundPrice);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mutualfund/{id}/prices")
    public List<MutualFundPrice> getMutualFundPrice(@PathVariable("id")UUID mutualFundId)
    {
        List<MutualFundPrice> mutualFundPriceList =new ArrayList<>();
        try {
            mutualFundPriceList = priceService.getMutualFundPrice(mutualFundId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundPriceList;
    }

}
