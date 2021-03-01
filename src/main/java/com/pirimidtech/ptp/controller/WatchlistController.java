package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    AssetDetailRepository assetDetailRepository;
    @Autowired
    private WatchlistService watchlistService;
    @Autowired
    private WatchlistEntryService watchlistEntryService;
    @Autowired
    private AssetService assetService;

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Page<WatchlistEntry>> getAllWatchlistEntry(@PathVariable UUID watchlistId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        log.info("WatchlistId {} Requested all watchlist entries, page {} size {}", watchlistId.toString(), page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<WatchlistEntry> watchlistEntryPage = watchlistEntryService.getAllWatchlistEntryByWatchlistId(watchlistId, pageable);
        return ResponseEntity.ok().body(watchlistEntryPage);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<Watchlist>> getAllWatchlistId(@PathVariable UUID userId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        log.info("UserId {} requested all watchlist ids, page {} size {}", userId.toString(), page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Watchlist> watchlistPage = watchlistService.getWatchlistDetailByUserId(userId, pageable);
        return ResponseEntity.ok().body(watchlistPage);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AssetDetail>> searchNameLike(@RequestParam String name,
                                                            @RequestParam String asset) {
        AssetClass assetClass;
        if (asset.equalsIgnoreCase("stock")) {
            assetClass = AssetClass.STOCK;
        } else {
            assetClass = AssetClass.MUTUAL_FUND;
        }
        List<AssetDetail> searchName = new ArrayList<>(assetService.searchByNameAndAsset(name, assetClass));
        return ResponseEntity.ok().body(searchName);
    }

    @PostMapping("")
    public ResponseEntity<Watchlist> addWatchlist(@RequestBody Watchlist watchlist) {
        watchlist.setId(null);
        if (watchlist.getUser() == null || watchlist.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(watchlist);
        }
        watchlistService.add(watchlist);
        return ResponseEntity.ok().body(watchlist);
    }

    @PostMapping("/watchlistentry")
    public ResponseEntity<WatchlistEntry> addWatchlistEntry(@RequestBody WatchlistEntry watchlistEntry) {
        watchlistEntry.setId(null);
        if (watchlistEntry.getWatchlist() == null || watchlistEntry.getAssetDetail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(watchlistEntry);
        }
        if (watchlistService.findById(watchlistEntry.getWatchlist().getId()).isPresent()) {
            watchlistEntryService.add(watchlistEntry);
        }
        return ResponseEntity.ok().body(watchlistEntry);
    }

    @GetMapping("/searchWatchlist")
    public ResponseEntity<Page<WatchlistEntry>> searchInWatchlist(@RequestParam UUID watchlistID,
                                                                  @RequestParam String assetName,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        log.info("searching in watchlist with watchlistId {} keyword: {}",watchlistID,assetName);
        Pageable pageable = PageRequest.of(page, size);
        Page<WatchlistEntry> search = watchlistEntryService.searchByWatchlistIdAndAssetDetailName(watchlistID, assetName, pageable);
        return ResponseEntity.ok().body(search);
    }

    @DeleteMapping("/watchlistentry")
    public ResponseEntity<UUID> removeWatchlistEntry(@RequestParam UUID watchlistEntryId) {
        watchlistEntryService.remove(watchlistEntryId);
        return ResponseEntity.ok().body(watchlistEntryId);
    }
}
