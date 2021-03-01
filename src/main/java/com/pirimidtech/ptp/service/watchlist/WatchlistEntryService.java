package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WatchlistEntryService implements WatchlistEntryServiceInterface {

    @Autowired
    WatchlistEntryRepository watchlistEntryRepository;

    public Page<WatchlistEntry> getAllWatchlistEntryByWatchlistId(UUID watchlistId, Pageable pageable) {
        return watchlistEntryRepository.findByWatchlistId(watchlistId, pageable);
    }

    public Page<WatchlistEntry> searchByWatchlistIdAndAssetDetailName(UUID watchlistId, String assetName, Pageable pageable) {
        return watchlistEntryRepository.findByWatchlistIdAndAssetDetailNameContainingIgnoreCase(watchlistId, assetName, pageable);
    }

    public void remove(UUID watchlistEntryId) {
        watchlistEntryRepository.deleteById(watchlistEntryId);
    }

    public void add(WatchlistEntry watchlistEntry) {
        watchlistEntryRepository.save(watchlistEntry);
    }

}
