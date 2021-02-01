package com.pirimidtech.ptp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @RequestMapping(method= RequestMethod.GET,value = "/watchlist/stocks/{userId}")
    public List<Watchlist> displayStockWatchlist(@PathVariable String userId){

        List<Watchlist> stockData = watchlistService.getAllStockDetailByUserId(UUID.fromString(userId));
        return stockData;
    }

    @RequestMapping(method= RequestMethod.GET,value = "/watchlist/mutualfunds/{userId}")
    public List<Watchlist> displayMutualFundWatchlist(@PathVariable String userId){
        return watchlistService.getAllMutualFundDetailByUserId(UUID.fromString(userId));
    }

    @PostMapping("/watchlist/search")
    public List<CompanyDetail> search(@RequestBody CompanyDetail companyDetail){
        return watchlistService.searchCompanyNameLike(companyDetail.getName(),companyDetail.getAssetClass());
    }

    @PostMapping("/watchlist/add")
    public void add(@RequestBody Watchlist watchlist){
        List<Watchlist> addNew = new ArrayList();
        addNew.add(watchlist);
        watchlistService.addNewCompany(watchlist);
    }
}
