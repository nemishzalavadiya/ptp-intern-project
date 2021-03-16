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
    private static final float MIN = 20, MAX = 10000;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");

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
        for (int index = 0; index < companyNameList.size(); index++) {
            if (stockList.size() < companyNameList.size()) {
                stockList.add(generateStockDataFromCompany(index));
            } else {
                updateStockDetailsForStock(index);
            }
        }
    }

    private Stock generateStockDataFromCompany(int position) {
        DECIMAL_FORMAT.setRoundingMode(RoundingMode.DOWN);
        Stock stock = new Stock();
        stock.setCompanyId(companyIdList.get(position));
        stock.setCompanyName(companyNameList.get(position));
        stock.setTimestamp(new Date());
        stock.setOpen(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        stock.setClose(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        stock.setMarketPrice(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        stock.setHigh(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        stock.setLow(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        stock.setPercentageChange(Float.parseFloat(DECIMAL_FORMAT.format((Math.random() * 5))));
        //exchange value of high is lower then low
        if (stock.getHigh() < stock.getLow()) {
            float high = stock.getHigh();
            stock.setHigh(stock.getLow());
            stock.setLow(high);
        }
        return stock;
    }

    private void updateStockDetailsForStock(int index) {
        DECIMAL_FORMAT.setRoundingMode(RoundingMode.DOWN);
        float previousMarketPrice = stockList.get(index).getMarketPrice();
        stockList.get(index).setMarketPrice(Float.parseFloat(DECIMAL_FORMAT.format(MIN + (Math.random() * ((MAX - MIN) + 1)))));
        float currentMarketPrice = stockList.get(index).getMarketPrice();
        float currentPercentageChange = (currentMarketPrice - previousMarketPrice) / previousMarketPrice;
        stockList.get(index).setPercentageChange(Float.parseFloat(DECIMAL_FORMAT.format(currentPercentageChange)));
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
