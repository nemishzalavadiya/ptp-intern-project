package com.pirimidtech.ptp.service.datagenerator;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.service.asset.AssetService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DataGenerator {
    private List<Stock> stockList;
    private List<UUID> companyIdList;
    private List<String> companyNameList;
    private List<MutualFund> mutualFundList;

    @Autowired
    private AssetService assetService;

    @Autowired
    private MutualFundService mutualFundService;

    @PostConstruct
    public void init() {
        companyIdList = new ArrayList<>();
        companyNameList = new ArrayList<>();
        stockList = new ArrayList<>();
        mutualFundList = new ArrayList<>();
        Page<AssetDetail> assetServiceList = assetService.getAllAssetDetail(PageRequest.of(0, Integer.MAX_VALUE));
        assetServiceList.forEach(item -> {
            if (item.getAssetClass().compareTo(AssetClass.STOCK) == 0) {
                companyIdList.add(item.getId());
                companyNameList.add(item.getName());
            } else {
                mutualFundList.add(getMutualFundFromAsset(item));
            }
        });
    }

    public void setData() {

        stockList.clear();
        for (int i = 0; i < companyNameList.size(); i++) {
            stockList.add(generateStockDataFromCompany(i));
        }
    }

    private Stock generateStockDataFromCompany(int position) {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        float Min = 20, Max = 10000;
        Stock stock = new Stock();
        stock.setCompanyId(companyIdList.get(position));
        stock.setCompanyName(companyNameList.get(position));
        stock.setTimestamp(new Date());
        stock.setOpen(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
        stock.setClose(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
        stock.setMarketPrice(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
        stock.setHigh(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
        stock.setLow(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
        stock.setPercentageChange(Float.parseFloat(df.format((Math.random() * 5))));
        return stock;
    }

    public List<Stock> getGeneratedStockList() {
        return stockList;
    }
    public List<MutualFund> getGeneratedMutualFundList() {
        return mutualFundList;
    }

    private MutualFund getMutualFundFromAsset(AssetDetail assetDetail) {
        MutualFund mutualFund = new MutualFund();
        Optional<MutualFundStatistic> mutualFundStatistic = mutualFundService.getMutualFundStatisticByAssetId(assetDetail.getId());
        if (mutualFundStatistic.isPresent()) {
            mutualFund.setCompanyId(assetDetail.getId());
            mutualFund.setCompanyName(assetDetail.getName());
            mutualFund.setRisk(mutualFundStatistic.get().getRisk());
            mutualFund.setFundSize(mutualFundStatistic.get().getFundSize());
            mutualFund.setExpenseRatio(mutualFundStatistic.get().getExpenseRatio());
            mutualFund.setNav(mutualFundStatistic.get().getNav());
            mutualFund.setTimestamp(new Date());
        }
        return mutualFund;
    }
}
