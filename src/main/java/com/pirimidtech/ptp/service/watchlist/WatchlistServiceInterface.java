package com.pirimidtech.ptp.service.watchlist;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface WatchlistServiceInterface{
    List<Watchlist> getWatchlistDetailByUserId(UUID userId, AssetClass assetClass, Pageable pageable);
    void add(Watchlist watchlist);
    void remove(UUID watchlistId);
}
