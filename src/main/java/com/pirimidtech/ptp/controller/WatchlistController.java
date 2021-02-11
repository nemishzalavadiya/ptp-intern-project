package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import com.pirimidtech.ptp.repository.WatchlistRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/search")
    public List<AssetDetail> searchNameLike(@RequestParam String name,
                                            @RequestParam String assetClass,
                                            @RequestParam Integer page,
                                            @RequestParam Integer size){

        Pageable pageable = PageRequest.of(page, size);
        List<AssetDetail> searchName = new ArrayList<>();
        searchName.add(assetDetailRepository.findByNameContainingAndAssetClass(name,assetClass));
        return searchName;
    }

    @PostMapping("/")
    public ResponseEntity<Watchlist> addWatchlist(@RequestBody Watchlist watchlist){
        watchlist.setId(UUID.randomUUID());
        watchlistService.add(watchlist);
        return ResponseEntity.ok().body(watchlist);
    }

    @PostMapping("/addWatchlistEntry")
    public ResponseEntity<WatchlistEntry> addWatchlistEntry(@RequestBody WatchlistEntry watchlistEntry){
        watchlistEntry.setId(UUID.randomUUID());
        if(watchlistService.findById(watchlistEntry.getWatchlist().getId()).isPresent()) {
            watchlistEntryService.add(watchlistEntry);
        }
        return ResponseEntity.ok().body(watchlistEntry);
    }

    @DeleteMapping("")
    public ResponseEntity<UUID> removeAssetDetail(@RequestParam UUID watchlistEntryId){
        watchlistEntryService.remove(watchlistEntryId);
        return ResponseEntity.ok().body(watchlistEntryId);
    }
}
