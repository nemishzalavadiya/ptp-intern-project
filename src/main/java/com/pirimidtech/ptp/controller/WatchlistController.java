package com.pirimidtech.ptp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.MutualFundWatchlistDTO;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockService stockService;

    @Autowired
    private MutualFundService mutualFundService;

    @RequestMapping(method= RequestMethod.GET,value = "/watchlist/stocks/{userId}")
    public List<StockWatchlistDTO> displayStockWatchlist(@PathVariable String userId){

        List<StockWatchlistDTO> stockWatchlistDTOList = new ArrayList<>();
        List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(UUID.fromString(userId));

        userWatchlist.forEach((item)->{
            Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
            if(companyDetail.isPresent()) {
                //DTO object
                StockDetail stockDetail;
                stockDetail = stockService.getStockDetailByCompanyId(item.getCompanyDetail().getId());
                if(stockDetail!=null) {
                    Optional<StockStatistic> stockStatistic = stockService.getStockStatsById(stockDetail.getId());
                    if (stockStatistic.isPresent()) {
                        StockWatchlistDTO stockWatchlistDTO = new StockWatchlistDTO();
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

        return stockWatchlistDTOList;
    }
    @RequestMapping(method= RequestMethod.GET,value = "/watchlist/mutualfunds/{userId}")
    public List<MutualFundWatchlistDTO> displayMutualFundWatchlist(@PathVariable String userId){

        List<MutualFundWatchlistDTO> mutualFundWatchlistDTOList=new ArrayList<>();
        List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(UUID.fromString(userId));

        userWatchlist.forEach((item)->{
            Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getCompanyDetail().getId());
            if(companyDetail.isPresent()) {
                //DTO object
                MutualFundDetail mutualFundDetail = mutualFundService.getMutualFundDetailByCompanyId(companyDetail.get().getId());
                if(mutualFundDetail!=null) {
                    MutualFundStatistic mutualFundStatistic = mutualFundService.getMutualFundStatsById(mutualFundDetail.getId());
                    MutualFundWatchlistDTO mutualFundWatchlistDTO = new MutualFundWatchlistDTO();
                    mutualFundWatchlistDTO.setMinSIP(mutualFundStatistic.getMinSIP());
                    mutualFundWatchlistDTO.setNav(mutualFundStatistic.getNav());
                    mutualFundWatchlistDTO.setRisk(mutualFundWatchlistDTO.getRisk());
                    mutualFundWatchlistDTO.setName(companyDetail.get().getName());
                    mutualFundWatchlistDTO.setExpenseRatio(mutualFundWatchlistDTO.getExpenseRatio());

                    mutualFundWatchlistDTOList.add(mutualFundWatchlistDTO);
                }
            }
        });
        return mutualFundWatchlistDTOList;
    }
}
