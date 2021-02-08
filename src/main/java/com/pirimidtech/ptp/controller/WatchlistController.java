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
    public List<UUID> displayWatchlist(@PathVariable String assetClass, @PathVariable String userId, @RequestParam int page, @RequestParam int size){
        UUID userUuid;
        Pageable pageable;
        List<UUID> companyUuidList = new ArrayList<>();
        try{
            userUuid = UUID.fromString(userId); // throws IllegalArgumentException
            pageable = PageRequest.of(page,size);
            AssetClass assetType;
            if(assetClass.equalsIgnoreCase("stocks")){
                assetType=AssetClass.STOCK;
            }
            else{
                assetType=AssetClass.MUTUAL_FUND;
            }
            List<Watchlist> userWatchlist = watchlistService.getWatchlistDetailByUserId(userUuid, assetType,pageable);
            userWatchlist.forEach((item)->{
                    companyUuidList.add(item.getCompanyDetail().getId());
            });
        } catch (IllegalArgumentException exception){
            throw new ExceptionHandler("Invalid Input");
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getMessage());
        }
        return companyUuidList;
    }

    @PostMapping("/search")
    public List<CompanyDetail> searchNameLike(@RequestBody CompanyDetail companyDetail){
        List<CompanyDetail> searchName = new ArrayList<>();
        try {
            searchName.add(companyDetailRepository.findByNameContainingAndAssetClass(companyDetail.getName(),
                    companyDetail.getAssetClass()));
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return searchName;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCompany(@RequestBody Watchlist watchlist){
        try {
            watchlistService.add(watchlist);
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{watchlistId}")
    public ResponseEntity<Void> removeCompany(@PathVariable UUID watchlistId){
        try{
            watchlistService.remove(watchlistId);
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }
}
