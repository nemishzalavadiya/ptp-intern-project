package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.Watchlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface WatchlistServiceInterface{
    Page<Watchlist> getWatchlistDetailByUserId(UUID userId,Pageable pageable);
}
