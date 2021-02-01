package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StockDetailController {
    @Autowired
    private StockService stockService;

    @RequestMapping(method = RequestMethod.GET, value = "/stocks/details")
    public List<StockDetail> getAllStockDetails(){
        return stockService.getAllStockDetails();
    }

    @RequestMapping(method = RequestMethod.POST, value = "stocks/details/add")
    public void addStocks(@RequestBody StockDetail stockDetail){
        stockService.addStock(stockDetail);
    }

    @RequestMapping(method = RequestMethod.GET, value = "stocks/details/{id}")
    public Optional<StockDetail> getStockDetailsById(@PathVariable UUID id){
        return stockService.getStockDetailById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stocks/stats/add")
    public void addStockStats(StockStatistic stockStatistic){
        stockService.addStockStats(stockStatistic);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stocks/stats/{id}")
    public Optional<StockStatistic> getStockStatsById(@PathVariable UUID id){
        return stockService.getStockStatsById(id);
    }
}
