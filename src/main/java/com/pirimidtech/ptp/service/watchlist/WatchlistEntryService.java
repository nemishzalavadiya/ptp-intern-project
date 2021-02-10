package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WatchlistEntryService implements WatchlistEntryServiceInterface {

    @Autowired
    WatchlistEntryRepository watchlistEntryRepository;

    public Page<WatchlistEntry> getAllWatchlistEntryByWatchlistId(UUID watchlistId, Pageable pageable) {
        return watchlistEntryRepository.findByWatchlistId(watchlistId, pageable);
    }
}
