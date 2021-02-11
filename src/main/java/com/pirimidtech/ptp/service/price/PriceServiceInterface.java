package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;

import java.util.List;
import java.util.UUID;

public interface PriceServiceInterface {
    void addToStockPrice(StockPrice stockPrice);

    List<StockPrice> getStockPrice(UUID stockId, int pageNo, int pageSize);

    void addToMutualFundPrice(MutualFundPrice mutualFundPrice);

    List<MutualFundPrice> getMutualFundPrice(UUID mutualFundId, int pageNo, int pageSize);


}
