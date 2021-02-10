package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.WatchlistEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface WatchlistEntryServiceInterface {
    Page<WatchlistEntry> getAllWatchlistEntryByWatchlistId(UUID watchlistId, Pageable pageable);
}
