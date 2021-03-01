package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import java.util.UUID;

public interface OrderServiceInterface {
    StockTrade addToStockOrder(StockTrade stockTrade);

    Page<StockTrade> getAllStockOrder(UUID userId, int pageNo, int pageSize);

    StockTrade getStockOrder(UUID orderId);

    MutualFundOrder addToMutualFundOrder(MutualFundOrder mutualFundOrder);

    Page<MutualFundOrder> getAllMutualFundOrder(UUID userId, int pageNo, int pageSize);

    MutualFundOrder getMutualFundOrder(UUID orderId);

    Page<StockTrade> getStockOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws Exception;
}
