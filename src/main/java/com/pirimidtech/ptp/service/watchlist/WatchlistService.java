package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService implements WatchlistServiceInterface {

    @Autowired
    WatchListRepository watchListRepository;

    public List<Watchlist> getWatchlistDetailByUserId(UUID userId, AssetClass assetClass, Pageable pageable){
        return watchListRepository.findByUserIdAndCompanyDetailAssetClass(userId,assetClass,pageable);
    }

    public void add(Watchlist watchlist) {
        watchListRepository.save(watchlist);
    }

    public void remove(UUID watchlistId){
        watchListRepository.deleteById(watchlistId);
    }
}
