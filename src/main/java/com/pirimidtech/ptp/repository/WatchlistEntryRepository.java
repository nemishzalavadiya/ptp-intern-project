package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.WatchlistEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchlistEntryRepository extends JpaRepository<WatchlistEntry, UUID> {
    Page<WatchlistEntry> findByWatchlistId(UUID watchlistId, Pageable pageable);

    Page<WatchlistEntry> findByWatchlistIdAndAssetDetailNameContainingIgnoreCase(UUID watchlistId, String AssetName, Pageable pageable);
}
