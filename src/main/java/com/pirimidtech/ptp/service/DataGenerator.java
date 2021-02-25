package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.service.asset.AssetService;
import lombok.Getter;
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
@Getter
public class DataGenerator {
    private static List<DataGenerator> dataGeneratorList;
    private static List<UUID> companyIdList;
    private static List<String> companyNameList;
    private LocalDateTime timestamp;
    private UUID company_id;
    private String company_name;
    private float open;
    private float close;
    private float marketPrice;
    private float high;
    private float low;
    private float percentageChange;

    @Autowired
    private AssetService assetService;

    public void setDistinctCompany() {
        DataGenerator.companyIdList = new ArrayList<>();
        DataGenerator.companyNameList = new ArrayList<>();
        DataGenerator.dataGeneratorList = new ArrayList<>();
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
    }

    public static List<DataGenerator> getDataGeneratorList() {
        return dataGeneratorList;
    }
}
