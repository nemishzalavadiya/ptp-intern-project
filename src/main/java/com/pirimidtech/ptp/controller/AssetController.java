package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.DTO.DashboardDTO;
import com.pirimidtech.ptp.DTO.DashboardMutualFundDTO;
import com.pirimidtech.ptp.DTO.DashboardStockDTO;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.util.DashboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;

    @Autowired
    private StockService stockService;

    @Autowired
    private MutualFundService mutualFundService;

    @Autowired
    private DashboardUtil dashboardUtil;

    @GetMapping(value = "/assets/{id}")
    public Optional<AssetDetail> getAssetDetail(@PathVariable UUID id) {
        Optional<AssetDetail> assetDetail = assetService.getAssetDetail(id);
        if (!assetDetail.isPresent())
            throw new NotFoundException();
        return assetDetail;
    }

    @PostMapping(value = "/assets")
    public AssetDetail addAssetDetail(@RequestBody AssetDetail assetDetail) {
        assetService.addAsset(assetDetail);
        return assetService.getAssetDetail(assetDetail.getId()).get();
    }

    @GetMapping(value = "/assets")
    public Page<AssetDetail> assetDetailList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<AssetDetail> assetDetailList;
        assetDetailList = assetService.getAllAssetDetail(paging);
        return assetDetailList;
    }

    @GetMapping(value = "/assets/search/{infix}")
    public Page<AssetDetail> searchAssetList(@PathVariable String infix, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<AssetDetail> assetDetailList;
        assetDetailList = assetService.searchAssetDetail(infix, paging);
        return assetDetailList;
    }

    @GetMapping(value = "/assets/stocks/{assetId}")
    public Optional<StockStatistic> getStockStatisticByAssetId(@PathVariable UUID assetId) {
        Optional<StockStatistic> stockStatistic = stockService.getStockStatisticByAssetId(assetId);
        if (!stockStatistic.isPresent())
            throw new NotFoundException();
        return stockStatistic;
    }

    @GetMapping(value = "/assets/mutualfunds/{assetId}")
    public Optional<MutualFundStatistic> getMutualFundStatisticByAssetId(@PathVariable UUID assetId) {
        Optional<MutualFundStatistic> mutualFundStatistic = mutualFundService.getMutualFundStatisticByAssetId(assetId);
        if (!mutualFundStatistic.isPresent())
            throw new NotFoundException();
        return mutualFundStatistic;
    }
    @GetMapping(value = "/dashboard/assets")
    public DashboardDTO getAllAssetsForDashboard(){
        DashboardDTO dashboardDTO = new DashboardDTO();
        List<DashboardStockDTO> dashboardStockDTOList = dashboardUtil.getTopStocksByPeRatio();
        List<DashboardMutualFundDTO> dashboardMutualFundDTOList = dashboardUtil.getTopMutualFundsByRisk();
        dashboardDTO.setDashboardStockDTOList(dashboardStockDTOList);
        dashboardDTO.setDashboardMutualFundDTOList(dashboardMutualFundDTOList);
        return dashboardDTO;
    }
}