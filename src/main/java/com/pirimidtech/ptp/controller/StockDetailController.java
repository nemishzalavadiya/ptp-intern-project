package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class StockDetailController {
    @Autowired
    private StockService stockService;

    @GetMapping(value = "/stocks/details")
    public Page<StockDetail> getAllStockDetails(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<StockDetail> stockDetails;
        stockDetails = stockService.getAllStockDetails(paging);
        return stockDetails;
    }

    @PostMapping(value = "/stocks/details")
    public StockDetail addStocks(@RequestBody StockDetail stockDetail) {
        stockService.addStock(stockDetail);
        return stockService.getStockDetailById(stockDetail.getId()).get();
    }

    @GetMapping(value = "/stocks/details/{id}")
    public Optional<StockDetail> getStockDetailsById(@PathVariable UUID id) {
        Optional<StockDetail> stockDetail = stockService.getStockDetailById(id);
        if (!stockDetail.isPresent())
            throw new NotFoundException();
        return stockDetail;
    }

    @PostMapping(value = "/stocks/stats")
    public StockStatistic addStockStats(StockStatistic stockStatistic) {
        stockService.addStockStats(stockStatistic);
        return stockService.getStockStatsById(stockStatistic.getId()).get();
    }

    @GetMapping(value = "/stocks/stats/{id}")
    public Optional<StockStatistic> getStockStatsById(@PathVariable UUID id) {
        Optional<StockStatistic> stockStatistic = stockService.getStockStatsById(id);
        if (!stockStatistic.isPresent())
            throw new NotFoundException();
        return stockStatistic;
    }
}
