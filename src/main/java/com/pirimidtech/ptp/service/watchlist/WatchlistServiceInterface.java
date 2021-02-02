package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Watchlist;

import java.util.List;
import java.util.UUID;

public interface WatchlistServiceInterface{
    List<Watchlist> getAllWatchlistDetailByUserId(UUID userId);
    void add(Watchlist watchlist);
    void remove(UUID watchlistId);
}
