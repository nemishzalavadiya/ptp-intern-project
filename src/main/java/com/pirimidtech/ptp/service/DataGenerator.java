package com.pirimidtech.ptp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataGenerator {
    public static List<DataGenerator> dataGeneratorList = new ArrayList<>();
    public static List<UUID> companyIdList = new ArrayList<>();
    public LocalDateTime timestamp;
    public UUID company_id;
    public float open;
    public float close;
    public float marketPrice;
    public float high;
    public float low;
    public float percentageChange;

    public void setDistinctCompanyId() {
        companyIdList.add(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        companyIdList.add(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyIdList.add(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyIdList.add(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        companyIdList.add(UUID.fromString("00000000-0000-0000-0000-000000000004"));
    }

    public void setData() {
        float Min = 20, Max = 1000;
        if (dataGeneratorList.size() != companyIdList.size())
            DataGenerator.companyIdList.forEach((id) -> {
                DataGenerator dataGenerator = new DataGenerator();
                dataGenerator.company_id = id;
                dataGenerator.timestamp = LocalDateTime.now();
                dataGenerator.open = Min + (float) (Math.random() * ((Max - Min) + 1));
                dataGenerator.close = Min + (float) (Math.random() * ((Max - Min) + 1));
                dataGenerator.marketPrice = Min + (float) (Math.random() * ((Max - Min) + 1));
                dataGenerator.high = Min + (float) (Math.random() * ((Max - Min) + 1));
                dataGenerator.low = Min + (float) (Math.random() * ((Max - Min) + 1));
                dataGenerator.percentageChange = (float) (Math.random() * 5);
                dataGeneratorList.add(dataGenerator);
            });
        else
            dataGeneratorList.forEach((item) -> {
                item.timestamp = LocalDateTime.now();
                item.open = (float) Math.random() * 1000;
                item.close = (float) Math.random() * 1000;
                item.marketPrice = (float) Math.random() * 1000;
                item.high = (float) Math.random() * 1000;
                item.low = (float) Math.random() * 1000;
                item.percentageChange = (float) Math.random() * 100;
            });
    }
}
