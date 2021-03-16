package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Page<WatchlistEntry>> getAllWatchlistEntry(@PathVariable UUID watchlistId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        log.info("WatchlistId {} Requested all watchlist entries, page {} size {}", watchlistId.toString(), page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<WatchlistEntry> watchlistEntryPage = watchlistEntryService.getAllWatchlistEntryByWatchlistId(watchlistId, pageable);
        return ResponseEntity.ok().body(watchlistEntryPage);
    }

    @GetMapping("/user")
    public ResponseEntity<Page<Watchlist>> getAllWatchlistId(HttpServletRequest httpServletRequest, @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
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
    public ResponseEntity<WatchlistEntry> addWatchlistEntry(@RequestBody WatchlistEntry watchlistEntry,
                                                            HttpServletRequest httpServletRequest) {
        String jwtToken = requestUtil.getTokenFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Optional<User> user = userService.getUserById(userId);
        log.info("UserId {} requested all watchlist ids, page {} size {}", userId.toString());
        if (user.isPresent()){
            watchlistEntry.setId(null);
            ArrayList<Watchlist> watchlistList = new ArrayList<>(watchlistService.findByUserId(userId));
            Watchlist watchlist = new Watchlist();
            Optional<AssetDetail> assetDetail = assetService.getAssetDetail(watchlistEntry.getAssetDetail().getId());
            if(assetDetail.get().getAssetClass().equals(AssetClass.STOCK)){
                    watchlist.setUser(user.get());
                    watchlist.setId(watchlistList.get(0).getId());
            }
            else{
                watchlist.setUser(user.get());
                watchlist.setId(watchlistList.get(1).getId());
            }
                watchlistEntry.setWatchlist(watchlist);
                watchlistEntryService.add(watchlistEntry);
                watchlistEntry.setWatchlist(watchlist);

            return ResponseEntity.ok().body(watchlistEntry);
        }
        else{
            throw new NotFoundException();
        }
    }

    @GetMapping("/searchWatchlist")
    public ResponseEntity<Page<WatchlistEntry>> searchInWatchlist(@RequestParam UUID watchlistID,
                                                                  @RequestParam String assetName,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        log.info("searching in watchlist with watchlistId {} keyword: {}", watchlistID, assetName);
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
