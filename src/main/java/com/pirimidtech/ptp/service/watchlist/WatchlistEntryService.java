package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WatchlistEntryService implements WatchlistEntryServiceInterface {

    @Autowired
    WatchlistEntryRepository watchlistEntryRepository;

    public List<WatchlistEntry> getAllWatchlistEntryByWatchlistId(UUID watchlistId, Pageable pageable){
        return watchlistEntryRepository.findByWatchlistId(watchlistId,pageable);
    }
}
