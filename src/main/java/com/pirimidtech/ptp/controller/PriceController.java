package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/stock")
    public ResponseEntity addToStockPrice(@RequestBody StockPrice stockPrice)
    {
        priceService.addToStockPrice(stockPrice);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/stock/{stockId}")
    public List<StockPrice> getStockPrice(@PathVariable("stockId")UUID stockId)
    {
        return priceService.getStockPrice(stockId);
    }


}
