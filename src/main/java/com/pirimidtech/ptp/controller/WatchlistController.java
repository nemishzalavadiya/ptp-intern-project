package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private WatchlistEntryService watchlistEntryService;
    @Autowired
    private AssetService assetService;
    @Autowired
    AssetDetailRepository assetDetailRepository;

    @GetMapping("/{watchlistId}")
    public List<UUID> getAllWatchlistEntry(@PathVariable UUID watchlistId, @RequestParam int page, @RequestParam int size) {
        List<UUID> assetUuidList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        List<WatchlistEntry> watchlistEntryList = watchlistEntryService.getAllWatchlistEntryByWatchlistId(watchlistId, pageable);
        watchlistEntryList.forEach((watchlistEntry -> assetUuidList.add(watchlistEntry.getAssetDetail().getId())));
        return assetUuidList;
    }

    @GetMapping("/users/{userId}")
    public List<UUID> getAllWatchlistId(@PathVariable UUID userId) {
        List<UUID> watchlistUuidList = new ArrayList<>();
        List<Watchlist> watchlistList = watchlistService.getWatchlistDetailByUserId(userId);
        watchlistList.forEach((watchlist -> watchlistUuidList.add(watchlist.getId())));
        return watchlistUuidList;
    }
}
