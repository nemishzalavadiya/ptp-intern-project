package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {
    StockTrade addToStockOrder(StockTrade stockTrade);

    List<StockTrade> getAllStockOrder(UUID userId, int pageNo, int pageSize);

    StockTrade getStockOrder(UUID orderId);

    MutualFundOrder addToMutualFundOrder(MutualFundOrder mutualFundOrder);

    List<MutualFundOrder> getAllMutualFundOrder(UUID userId, int pageNo, int pageSize);

    MutualFundOrder getMutualFundOrder(UUID orderId);
}
