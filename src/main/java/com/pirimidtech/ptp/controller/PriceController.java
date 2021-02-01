package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/stock")
    public void addToStockPrice(@RequestBody StockPrice stockPrice)
    {
        priceService.addToStockPrice(stockPrice);
    }

    @GetMapping("/stock/{stockId}")
    public List<StockPrice> getStockPrice(@PathVariable("stockId")UUID stockId)
    {
        return priceService.getStockPrice(stockId);
    }

    @PostMapping("/mutualFund")
    public void addToMutualFundPrice(@RequestBody MutualFundPrice mutualFundPrice)
    {
        priceService.addToMutualFundPrice(mutualFundPrice);
    }

    @GetMapping("/mutualFund/{mutualFundId}")
    public List<MutualFundPrice> getMutualFundPrice(@PathVariable("mutualFundId")UUID mutualFundId)
    {
        return priceService.getMutualFundPrice(mutualFundId);
    }

}
