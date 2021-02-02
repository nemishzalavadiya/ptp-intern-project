package com.pirimidtech.ptp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.MutualFundWatchlistDTO;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.exception.WatchlistException;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method= RequestMethod.GET,value = "/stocks/{userId}")
    public List<StockWatchlistDTO> displayStockWatchlist(@PathVariable String userId){
        UUID userUuid;
        List<StockWatchlistDTO> stockWatchlistDTOList = new ArrayList<>();
        StockWatchlistDTO stockWatchlistDTO = new StockWatchlistDTO();
        try{
            userUuid = UUID.fromString(userId); // throws IllegalArgumentException
            List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(userUuid);

            userWatchlist.forEach((item)->{
                Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
                if(companyDetail.isPresent()) {
                    StockDetail stockDetail;
                    stockDetail = stockService.getStockDetailByCompanyId(item.getCompanyDetail().getId());
                    if(stockDetail!=null) {
                        Optional<StockStatistic> stockStatistic = stockService.getStockStatsById(stockDetail.getId());
                        if (stockStatistic.isPresent()) {
                            stockWatchlistDTO.setName(companyDetail.get().getName());
                            stockWatchlistDTO.setOpenPrice(0.0f);
                            stockWatchlistDTO.setClosePrice(0.0f);
                            stockWatchlistDTO.setTradePrice(stockStatistic.get().getMarketCap());
                            stockWatchlistDTO.setPercentageChange(0.0f);

                            stockWatchlistDTOList.add(stockWatchlistDTO);
                        }
                    }
                }
            });
        } catch (IllegalArgumentException exception){
            throw new WatchlistException("Invalid Input");
        }
        catch (Exception exception){
            throw new WatchlistException(exception.getCause());
        }
        return stockWatchlistDTOList;
    }
    @RequestMapping(method= RequestMethod.GET,value = "/mutualfunds/{userId}")
    public List<MutualFundWatchlistDTO> displayMutualFundWatchlist(@PathVariable String userId){

        UUID userUuid;
        List<MutualFundWatchlistDTO> mutualFundWatchlistDTOList = new ArrayList<>();
        MutualFundWatchlistDTO mutualFundWatchlistDTO = new MutualFundWatchlistDTO();
        try {
            userUuid = UUID.fromString(userId); // throws IllegalArgumentException
            List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(userUuid);

            userWatchlist.forEach((item) -> {
                Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
                if (companyDetail.isPresent()) {
                    MutualFundDetail mutualFundDetail = mutualFundService.getMutualFundDetailByCompanyId(companyDetail.get().getId());
                    if (mutualFundDetail != null) {
                        MutualFundStatistic mutualFundStatistic = mutualFundService.getMutualFundStatsById(mutualFundDetail.getId());
                        if(mutualFundStatistic!=null) {
                            mutualFundWatchlistDTO.setMinSIP(mutualFundStatistic.getMinSIP());
                            mutualFundWatchlistDTO.setNav(mutualFundStatistic.getNav());
                            mutualFundWatchlistDTO.setRisk(mutualFundWatchlistDTO.getRisk());
                            mutualFundWatchlistDTO.setName(companyDetail.get().getName());
                            mutualFundWatchlistDTO.setExpenseRatio(mutualFundWatchlistDTO.getExpenseRatio());

                            mutualFundWatchlistDTOList.add(mutualFundWatchlistDTO);
                        }
                    }
                }
            });
        }catch (IllegalArgumentException exception){
            throw new WatchlistException("Invalid Input");
        }
        catch (Exception exception){
            throw new WatchlistException(exception.getCause());
        }
        return mutualFundWatchlistDTOList;
    }
}
