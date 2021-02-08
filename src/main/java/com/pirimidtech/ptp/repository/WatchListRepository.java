package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Watchlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface WatchListRepository extends JpaRepository<Watchlist, UUID> {
    List<Watchlist> findByUserIdAndCompanyDetailAssetClass(UUID userId, AssetClass assetClass, Pageable pageable);
    void deleteById(UUID watchlistId);
}
