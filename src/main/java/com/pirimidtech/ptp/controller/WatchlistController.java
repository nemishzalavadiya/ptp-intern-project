package com.pirimidtech.ptp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.entity.Watchlist;
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

        List<StockWatchlistDTO> stockWatchlistDTOList=new ArrayList<>();
        List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(UUID.fromString(userId));

        userWatchlist.forEach((item)->{
            Optional<CompanyDetail> companyDetail = companyService.getCompanyDetail(item.getId());
            if(companyDetail.isPresent()) {
                //DTO object
                Optional<StockStatistic> stockStatistic = stockService.getStockStatsById(item.getId());
                StockWatchlistDTO stockWatchlistDTO = new StockWatchlistDTO();
                stockWatchlistDTO.setName(companyDetail.get().getName());
                stockWatchlistDTO.setOpenPrice(0.0f);
                stockWatchlistDTO.setClosePrice(0.0f);
                stockWatchlistDTO.setTradePrice(stockStatistic.get().getMarketCap());
                stockWatchlistDTO.setPercentageChange(0.0f);

                stockWatchlistDTOList.add(stockWatchlistDTO);
            }
        });

        return stockWatchlistDTOList;
    }
    @RequestMapping(method= RequestMethod.GET,value = "/watchlist/mutualfunds/{userId}")
    public List<MutualFundWatchlistDTO> displayMutualFundWatchlist(@PathVariable String userId){

        List<MutualFundWatchlistDTO> mutualFundWatchlistDTOList=new ArrayList<>();
        List<Watchlist> userWatchlist = watchlistService.getAllWatchlistDetailByUserId(UUID.fromString(userId));

        userWatchlist.forEach((item)->{
            Optional<CompanyDetail> companyDetail = mutualFundService.getMutualFundDetailsById(item.getId())
            if(companyDetail.isPresent()) {
                //DTO object
                Optional<MutualFundStatistic> mutualFundStatistic = Optional.ofNullable(mutualFundService.getMutualFundStatsById(item.getId()));
                MutualFundWatchlistDTO mutualFundWatchlistDTO=new MutualFundWatchlistDTO();
                mutualFundWatchlistDTO.setMinSIP(mutualFundStatistic.get().getMinSIP());
                mutualFundWatchlistDTO.setNav(mutualFundStatistic.get().getNav());
                mutualFundWatchlistDTO.setRisk(mutualFundWatchlistDTO.getRisk());
                mutualFundWatchlistDTO.setName(companyDetail.get().getName());
                mutualFundWatchlistDTO.setExpenseRatio(mutualFundWatchlistDTO.getExpenseRatio());

                mutualFundWatchlistDTOList.add(mutualFundWatchlistDTO);
            }
        });
        return mutualFundWatchlistDTOList;
    }

    @PostMapping("/watchlist/search")
    public List<CompanyDetail> search(@RequestBody CompanyDetail companyDetail){
        return watchlistService.searchCompanyNameLike(companyDetail.getName(),companyDetail.getAssetClass().name());
    }

    @PostMapping("/watchlist/add")
    public void add(@RequestBody Watchlist watchlist){
        List<Watchlist> addNew = new ArrayList();
        addNew.add(watchlist);
        watchlistService.addNewCompany(watchlist);
    }
}
