package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<WatchlistEntry>> getAllWatchlistEntry(@PathVariable UUID watchlistId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WatchlistEntry> watchlistEntryPage = watchlistEntryService.getAllWatchlistEntryByWatchlistId(watchlistId, pageable);
        return ResponseEntity.ok().body(watchlistEntryPage);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<Watchlist>> getAllWatchlistId(@PathVariable UUID userId,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Watchlist> watchlistPage = watchlistService.getWatchlistDetailByUserId(userId,pageable);
        return ResponseEntity.ok().body(watchlistPage);
    }
}
