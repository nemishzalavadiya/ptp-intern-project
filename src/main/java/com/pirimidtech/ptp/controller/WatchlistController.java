package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.exception.ExceptionHandler;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CompanyService companyService;

    @Autowired
    CompanyDetailRepository companyDetailRepository;

    @GetMapping("/{assetClass}/{userId}")
    public List<UUID> displayWatchlist(@PathVariable String assetClass, @PathVariable UUID userId, @RequestParam int page, @RequestParam int size){
        List<UUID> assetUuidList=new ArrayList<>();
        return assetUuidList;
    }
}
