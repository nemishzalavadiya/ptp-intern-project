package com.pirimidtech.ptp.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void setDistinctCompanyId() {
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b181"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b182"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b183"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b184"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b185"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b186"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b187"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b188"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b189"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b190"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b191"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b192"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b193"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b194"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b195"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b196"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b197"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b198"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b199"));
        companyIdList.add(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b200"));
        companyNameList.add("China Petroleum & Chemical Corp. (SNP)");
        companyNameList.add("Royal Dutch Shell PLC (RDS. A)");
        companyNameList.add("Bharat Sanchar Nigam Limited");
        companyNameList.add("Berkshire Hathaway Inc. (BRK.A)");
        companyNameList.add("Toyota Motor Corp. (TM)");
        companyNameList.add("Oil and Natural Gas Corporation");
        companyNameList.add("CVS Health Corp. (CVS)");
        companyNameList.add("Bonn Group of Industries");
        companyNameList.add("Cathay Pacific Airways Ltd. (CPA)");
        companyNameList.add("LOCUS INTERNATIONAL CENTRE FOR ENTREPRENEURSHIP DEVELOPMENT AND INCUBATION SERVICES LIMITED");
        companyNameList.add("British Airways");
        companyNameList.add("Indian Private Corporation");
        companyNameList.add("Bharat Oil Fund Risers");
        companyNameList.add("Gujarat Solid Corporation");
        companyNameList.add("Pirimid Software TechGiant");
        companyNameList.add("Aoba Chorasi Technology");
        companyNameList.add("Motorola Tech Pharma");
        companyNameList.add("Sivaji Private Corporations");
        companyNameList.add("Nadani Private Organization");
        companyNameList.add("World Wide Private Library Fund Raiser");
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
