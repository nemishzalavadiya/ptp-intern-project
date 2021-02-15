package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface PriceServiceInterface {
    void addToStockPrice(StockPrice stockPrice);

    List<StockPrice> getStockPrice(UUID stockDetailId, String startDate, String endDate) throws ParseException;

    void addToMutualFundPrice(MutualFundPrice mutualFundPrice);

    List<MutualFundPrice> getMutualFundPrice(UUID mutualFundId, String startDate, String endDate) throws ParseException;

}
