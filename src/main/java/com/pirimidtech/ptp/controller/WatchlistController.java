package com.pirimidtech.ptp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.MutualFundWatchlistDTO;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.exception.ExceptionHandler;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockService stockService;

    @Autowired
    private MutualFundService mutualFundService;

    @Autowired
    CompanyDetailRepository companyDetailRepository;

    @RequestMapping(method= RequestMethod.GET,value = "/stocks/{userId}/{number}/{size}")
    public List<StockWatchlistDTO> displayStockWatchlist(@PathVariable String userId,@PathVariable int number,@PathVariable int size){
        UUID userUuid;
        List<StockWatchlistDTO> stockWatchlistDTOList = new ArrayList<>();
        Pageable pageable;
        try{
            userUuid = UUID.fromString(userId); // throws IllegalArgumentException
            pageable = PageRequest.of(number,size);
            List<Watchlist> userWatchlist = watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.STOCK,pageable);
            userWatchlist.forEach((item)->{
                Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
                if(companyDetail.isPresent()) {
                    StockWatchlistDTO stockWatchlistDTO = new StockWatchlistDTO();
                    stockWatchlistDTO.setName(companyDetail.get().getName());
                    stockWatchlistDTO.setOpenPrice(0.0f);
                    stockWatchlistDTO.setClosePrice(0.0f);
                    stockWatchlistDTO.setTradePrice(0.0f);
                    stockWatchlistDTO.setPercentageChange(0.0f);
                    stockWatchlistDTOList.add(stockWatchlistDTO);
                }
            });
        } catch (IllegalArgumentException exception){
            throw new ExceptionHandler("Invalid Input");
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return stockWatchlistDTOList;
    }

    @RequestMapping(method= RequestMethod.GET,value = "/mutualfunds/{userId}/{number}/{size}")
    public List<MutualFundWatchlistDTO> displayMutualFundWatchlist(@PathVariable String userId,@PathVariable int number,@PathVariable int size){

        UUID userUuid;
        List<MutualFundWatchlistDTO> mutualFundWatchlistDTOList = new ArrayList<>();
        Pageable pageable;
        try {
            userUuid = UUID.fromString(userId); // throws IllegalArgumentException
            pageable=  PageRequest.of(number,size);
            List<Watchlist> userWatchlist = watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.MUTUAL_FUND,pageable);
            userWatchlist.forEach((item) -> {
                Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
                if (companyDetail.isPresent()) {
                    Optional<MutualFundDetail> mutualFundDetail = mutualFundService.getMutualFundDetailByCompanyId(companyDetail.get().getId());
                    if (mutualFundDetail.isPresent()) {
                        Optional<MutualFundStatistic> mutualFundStatistic = mutualFundService.getMutualFundStatsById(mutualFundDetail.get().getId());
                        if(mutualFundStatistic.isPresent()) {
                            MutualFundWatchlistDTO mutualFundWatchlistDTO = new MutualFundWatchlistDTO();
                            mutualFundWatchlistDTO.setMinSIP(mutualFundStatistic.get().getMinSIP());
                            mutualFundWatchlistDTO.setNav(mutualFundStatistic.get().getNav());
                            mutualFundWatchlistDTO.setRisk(mutualFundStatistic.get().getRisk());
                            mutualFundWatchlistDTO.setName(companyDetail.get().getName());
                            mutualFundWatchlistDTO.setExpenseRatio(mutualFundStatistic.get().getExpenseRatio());
                            mutualFundWatchlistDTOList.add(mutualFundWatchlistDTO);
                        }
                    }
                }
            });
        }catch (IllegalArgumentException exception){
            throw new ExceptionHandler("Invalid Input");
        }
        catch (Exception exception){
            throw new ExceptionHandler(exception.getCause());
        }
        return mutualFundWatchlistDTOList;
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
