package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.Watchlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, UUID> {
    Page<Watchlist> findByUserId(UUID userId, Pageable pageable);
    List<Watchlist> findByUserId(UUID userId);

    Watchlist findByUserIdAndAndName(UUID userId, String toString);
}
