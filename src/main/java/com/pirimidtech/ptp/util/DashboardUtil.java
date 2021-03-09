package com.pirimidtech.ptp.util;

import com.pirimidtech.ptp.DTO.DashboardMutualFundDTO;
import com.pirimidtech.ptp.DTO.DashboardStockDTO;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DashboardUtil {

    @Autowired
    private StockService stockService;

    @Autowired
    private MutualFundService mutualFundService;

    public List<DashboardStockDTO> getTopStocksByPeRatio() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC,"peRatio"));
        List<DashboardStockDTO> dashboardStockDTOList = new ArrayList<>();
        List<StockStatistic> stockStatisticList = stockService.getAllStockStatistics(pageable).toList();
        stockStatisticList.forEach((item) -> {
            DashboardStockDTO dashboardStockDTO = createDashboardStockDTOFromStockStatistic(item);
            dashboardStockDTOList.add(dashboardStockDTO);
        });
        return dashboardStockDTOList;
    }

    public List<DashboardMutualFundDTO> getTopMutualFundsByRisk() {
        Pageable pageable = PageRequest.of(0, 4);
        List<DashboardMutualFundDTO> dashboardMutualFundDTOList = new ArrayList<>();
        List<MutualFundStatistic> mutualFundStatisticList = mutualFundService.getAllMutualFundsStatistics(pageable).toList();
        mutualFundStatisticList.forEach((item) -> {
            DashboardMutualFundDTO dashboardMutualFundDTO = createDashboardMutualFundDTOFromMutualFundStatistic(item);
            dashboardMutualFundDTOList.add(dashboardMutualFundDTO);
        });
        return dashboardMutualFundDTOList;
    }

    private DashboardStockDTO createDashboardStockDTOFromStockStatistic(StockStatistic stockStatistic) {
        DashboardStockDTO dashboardStockDTO = new DashboardStockDTO();
        dashboardStockDTO.setPeRatio(stockStatistic.getPeRatio());
        dashboardStockDTO.setName(stockStatistic.getStockDetail().getAssetDetail().getName());
        dashboardStockDTO.setIconUrl(stockStatistic.getStockDetail().getAssetDetail().getLogoUrl());
        return dashboardStockDTO;
    }

    private DashboardMutualFundDTO createDashboardMutualFundDTOFromMutualFundStatistic(MutualFundStatistic mutualFundStatistic) {
        DashboardMutualFundDTO dashboardMutualFundDTO = new DashboardMutualFundDTO();
        dashboardMutualFundDTO.setRisk(mutualFundStatistic.getRisk());
        dashboardMutualFundDTO.setName(mutualFundStatistic.getMutualFundDetail().getAssetDetail().getName());
        dashboardMutualFundDTO.setIconUrl(mutualFundStatistic.getMutualFundDetail().getAssetDetail().getLogoUrl());
        return dashboardMutualFundDTO;
    }
}
