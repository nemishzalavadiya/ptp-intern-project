package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WatchlistService implements WatchlistServiceInterface {

    @Autowired
    WatchlistRepository watchListRepository;

    public Page<Watchlist> getWatchlistDetailByUserId(UUID userId, Pageable pageable) {
        return watchListRepository.findByUserId(userId, pageable);
    }

    public void add(Watchlist watchlist) {
        watchListRepository.save(watchlist);
    }

    public Optional<Watchlist> findById(UUID watchlistId) {
        return watchListRepository.findById(watchlistId);
    }

    public List<Watchlist> findByUserId(UUID userId) {
        return watchListRepository.findByUserId(userId);
    }
}
