package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService implements WatchlistServiceInterface {
    @Autowired
    private WatchListRepository watchListRepository;

    public List<Watchlist> getAllStockDetailByUserId(UUID userId){
        List<Watchlist> stockDetailList = new ArrayList<>();
        watchListRepository.findByUserId(userId).forEach((item)->{
            if(item.getAssetClass()== AssetClass.STOCK){
                stockDetailList.add(item);
            }
        });
        return stockDetailList;
    }
    public List<Watchlist> getAllMutualFundDetailByUserId(UUID userId){
        List<Watchlist> mutualFundDetailList = new ArrayList<>();
        watchListRepository.findByUserId(userId).forEach((item)->{
            if(item.getAssetClass()== AssetClass.MUTUAL_FUND){
                mutualFundDetailList.add(item);
            }
        });
        return mutualFundDetailList;
    }
}
