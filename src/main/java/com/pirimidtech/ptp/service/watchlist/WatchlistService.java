package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService implements WatchlistServiceInterface {

    @Autowired
    WatchlistRepository watchListRepository;

    public List<Watchlist> getWatchlistDetailByUserId(UUID userId){
        return watchListRepository.findByUserId(userId);
    }
}
