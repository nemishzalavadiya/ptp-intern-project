package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.SIPStatus;
import com.pirimidtech.ptp.entity.StockTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import java.util.Date;
import java.util.List;

import java.util.UUID;

public interface OrderServiceInterface {
    StockTrade addToStockOrder(StockTrade stockTrade);

    Page<StockTrade> getAllStockOrder(UUID userId, int pageNo, int pageSize);

    StockTrade getStockOrder(UUID orderId);

    MutualFundOrder addToMutualFundOrder(MutualFundOrder mutualFundOrder);

    Page<MutualFundOrder> getAllMutualFundBySipStatus(UUID userId, Pageable pageable);

    MutualFundOrder getMutualFundOrder(UUID orderId);

    Page<StockTrade> getStockOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws Exception;

    MutualFundOrder updateMutualFundOrder(UUID id, MutualFundOrder newMutualFundOrder);

    Page<MutualFundOrder> getMutualFundOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws ParseException;


    }
