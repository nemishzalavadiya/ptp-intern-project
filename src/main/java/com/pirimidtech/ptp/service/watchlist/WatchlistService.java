package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService implements WatchlistServiceInterface {

    @Autowired
    WatchListRepository watchListRepository;

    public List<Watchlist> getAllWatchlistDetailByUserId(UUID userId){
        return watchListRepository.findByUserId(userId);
    }

    public void add(Watchlist watchlist) {
        watchListRepository.save(watchlist);
    }

    public void remove(UUID watchlistId){
        watchListRepository.deleteById(watchlistId);
    }
}
