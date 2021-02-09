package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.WatchlistEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WatchlistEntryRepository extends JpaRepository<WatchlistEntry, UUID> {
    List<WatchlistEntry> findByWatchlistId(UUID watchlistId, Pageable pageable);
}
