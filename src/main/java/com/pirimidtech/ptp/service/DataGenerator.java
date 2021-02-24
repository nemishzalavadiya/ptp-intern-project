package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.service.asset.AssetService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@ToString
public class DataGenerator {
    public static List<DataGenerator> dataGeneratorList = new ArrayList<>();
    public static List<UUID> companyIdList = new ArrayList<>();
    public static List<String> companyNameList = new ArrayList<>();
    public LocalDateTime timestamp;
    public UUID company_id;
    public String company_name;
    public float open;
    public float close;
    public float marketPrice;
    public float high;
    public float low;
    public float percentageChange;

    @Autowired
    AssetService assetService;

    public void setDistinctCompany() {
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
        if (dataGeneratorList.size() != companyIdList.size()) {
            for (int i = 0; i < companyNameList.size(); i++) {
                DataGenerator dataGenerator = new DataGenerator();
                dataGenerator.company_id = companyIdList.get(i);
                dataGenerator.company_name = companyNameList.get(i);
                dataGenerator.timestamp = LocalDateTime.now();
                dataGenerator.open = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                dataGenerator.close = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                dataGenerator.marketPrice = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                dataGenerator.high = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                dataGenerator.low = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                dataGenerator.percentageChange = Float.parseFloat(df.format((Math.random() * 5)));
                dataGeneratorList.add(dataGenerator);
            }
        } else
            dataGeneratorList.forEach((item) -> {
                item.timestamp = LocalDateTime.now();
                item.open = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                item.close = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                item.marketPrice = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                item.high = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                item.low = Float.parseFloat(df.format(Min + (Math.random() * ((Max - Min) + 1))));
                item.percentageChange = Float.parseFloat(df.format((Math.random() * 5)));
            });
    }
}
