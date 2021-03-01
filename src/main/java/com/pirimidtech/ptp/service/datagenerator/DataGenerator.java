package com.pirimidtech.ptp.service.datagenerator;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DataGenerator {
    private List<Stock> stockList;
    private List<UUID> companyIdList;
    private List<String> companyNameList;

    @Autowired
    private AssetService assetService;

    @PostConstruct
    public void init() {
        companyIdList = new ArrayList<>();
        companyNameList = new ArrayList<>();
        stockList = new ArrayList<>();
        Page<AssetDetail> assetServiceList = assetService.getAllAssetDetail(PageRequest.of(0, Integer.MAX_VALUE));
        assetServiceList.forEach(item -> {
            companyIdList.add(item.getId());
            companyNameList.add(item.getName());
        });
    }

    public void setData() {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        float Min = 20, Max = 10000;
        stockList.clear();
        for (int i = 0; i < companyNameList.size(); i++) {
            Stock stock = new Stock();
            stock.setCompany_id(companyIdList.get(i));
            stock.setCompany_name(companyNameList.get(i));
            stock.setTimestamp(new Date());
            stock.setOpen(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
            stock.setClose(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
            stock.setMarketPrice(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
            stock.setHigh(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
            stock.setLow(Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1)))));
            stock.setPercentageChange(Float.parseFloat(df.format((Math.random() * 5))));
            stockList.add(stock);
        }
    }

    public List<Stock> getGeneratedStockList() {
        return stockList;
    }
}
