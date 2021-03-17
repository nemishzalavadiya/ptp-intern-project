package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.SelectedStocksFilter;
import com.pirimidtech.ptp.DTO.StockStatisticDTO;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StockDetailController {
    @Autowired
    private StockService stockService;
    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private WatchlistEntryRepository watchlistEntryRepository;
    @Autowired
    private RequestUtil requestUtil;

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

    @PostMapping("/stocks/filters")
    public PageImpl<StockStatisticDTO> fundsFilter(HttpServletRequest httpServletRequest, @RequestBody SelectedStocksFilter selectedStocksFilter, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String sortingField, @RequestParam(defaultValue = "") String orderBy) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Pageable paging = PageRequest.of(page, size);
        Page<StockStatistic> stockStatistics = stockService.getStockFilterResults(selectedStocksFilter, paging, sortingField, orderBy);
        List<StockStatistic> stockStatisticsList = stockStatistics.toList();
        Watchlist watchlist = watchlistService.getWatchlistDetailByUserId(userId, AssetClass.STOCK);
        List<StockStatisticDTO> stockStatisticDTOList = new ArrayList<>();
        stockStatisticsList.forEach(element -> {
            Optional<WatchlistEntry> watchlistEntry = watchlistEntryRepository.findByAssetDetailIdAndAndWatchlistId(element.getStockDetail().getAssetDetail().getId(), watchlist.getId());
            StockStatisticDTO StockStatisticDTO = new StockStatisticDTO(element, watchlistEntry.isPresent());
            stockStatisticDTOList.add(StockStatisticDTO);
        });
        int contentSize = stockStatisticDTOList.size();
        long total = paging.getOffset() + contentSize + (contentSize == size ? size : 0);
        return new PageImpl<StockStatisticDTO>(stockStatisticDTOList, paging, total);
    }
}
