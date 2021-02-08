package com.pirimidtech.ptp.service.order;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {
    void addToStockOrder(StockOrder stockOrder);

    List<StockOrder> getAllStockOrder(UUID userId,int pageNo,int pageSize);

    StockOrder getStockOrder(UUID orderId);

    void deleteStockOrder(UUID orderId);

    void addToMutualFundOrder(MutualFundOrder mutualFundOrder);

    List<MutualFundOrder> getAllMutualFundOrder(UUID userId,int pageNo,int pageSize);

    MutualFundOrder getMutualFundOrder(UUID orderId);

    void deleteMutualFundOrder(UUID mutualFundOrderId);
}
