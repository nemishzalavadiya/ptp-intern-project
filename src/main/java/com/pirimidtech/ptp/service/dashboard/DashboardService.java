package com.pirimidtech.ptp.service.dashboard;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService implements DashboardServiceInterface {

    private static final String PE_RATION = "peRatio";

    private static final String RISK = "risk";

    private static final int PAGE = 0;

    private static final int SIZE = 4;

    @Autowired
    private StockService stockService;

    @Autowired
    private MutualFundService mutualFundService;

    public List<DashboardStockDTO> getTopStocksByPeRatio() {
        Pageable pageable = PageRequest.of(PAGE, SIZE, Sort.by(Sort.Direction.DESC, PE_RATION));
        List<DashboardStockDTO> dashboardStockDTOList = new ArrayList<>();
        List<StockStatistic> stockStatisticList = stockService.getAllStockStatistics(pageable).toList();
        dashboardStockDTOList = stockStatisticList.stream().map(this::createDashboardStockDTOFromStockStatistic).collect(Collectors.toList());
        return dashboardStockDTOList;
    }

    public List<DashboardMutualFundDTO> getTopMutualFundsByRisk() {
        Pageable pageable = PageRequest.of(PAGE, SIZE, Sort.by(Sort.Direction.DESC, RISK));
        List<DashboardMutualFundDTO> dashboardMutualFundDTOList = new ArrayList<>();
        List<MutualFundStatistic> mutualFundStatisticList = mutualFundService.getAllMutualFundsStatistics(pageable).toList();
        dashboardMutualFundDTOList = mutualFundStatisticList.stream().map(this::createDashboardMutualFundDTOFromMutualFundStatistic).collect(Collectors.toList());
        return dashboardMutualFundDTOList;
    }

    private DashboardStockDTO createDashboardStockDTOFromStockStatistic(StockStatistic stockStatistic) {
        DashboardStockDTO dashboardStockDTO = new DashboardStockDTO();
        dashboardStockDTO.setPeRatio(stockStatistic.getPeRatio());
        dashboardStockDTO.setName(stockStatistic.getStockDetail().getAssetDetail().getName());
        dashboardStockDTO.setIconUrl(stockStatistic.getStockDetail().getAssetDetail().getLogoUrl());
        dashboardStockDTO.setAssetDetailId(stockStatistic.getStockDetail().getAssetDetail().getId());
        return dashboardStockDTO;
    }

    private DashboardMutualFundDTO createDashboardMutualFundDTOFromMutualFundStatistic(MutualFundStatistic mutualFundStatistic) {
        DashboardMutualFundDTO dashboardMutualFundDTO = new DashboardMutualFundDTO();
        dashboardMutualFundDTO.setRisk(mutualFundStatistic.getRisk());
        dashboardMutualFundDTO.setName(mutualFundStatistic.getMutualFundDetail().getAssetDetail().getName());
        dashboardMutualFundDTO.setIconUrl(mutualFundStatistic.getMutualFundDetail().getAssetDetail().getLogoUrl());
        dashboardMutualFundDTO.setAssetDetailId(mutualFundStatistic.getMutualFundDetail().getAssetDetail().getId());
        return dashboardMutualFundDTO;
    }
}
