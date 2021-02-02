package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface WatchListRepository extends JpaRepository<Watchlist, UUID> {
    List<Watchlist> findByUserId(UUID userId);
}
