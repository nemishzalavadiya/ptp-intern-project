package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.SIPStatus;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



@Service
public class OrderService implements OrderServiceInterface {

    String dateFormat = "yyyy-MM-dd";
    String dateFormatWithTime = "E MMM dd HH:mm:ss Z yyyy";

    @Autowired
    private StockTradeRepository stockTradeRepository;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Override
    public StockTrade addToStockOrder(StockTrade stockTrade) {
        stockTrade.setTimestamp(new Date());
        return stockTradeRepository.save(stockTrade);
    }

    @Override
    public Page<StockTrade> getAllStockOrder(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StockTrade> pageResult = stockTradeRepository.findAllByUserIdOrderByTimestampDesc(userId, pageable);
        return pageResult;
    }

    @Override
    public StockTrade getStockOrder(UUID orderId) {
        Optional<StockTrade> stockOrder = stockTradeRepository.findById(orderId);
        return stockOrder.isPresent() ? stockOrder.get() : null;
    }

    @Override
    public MutualFundOrder addToMutualFundOrder(MutualFundOrder mutualFundOrder) {
        return mutualFundOrderRepository.save(mutualFundOrder);
    }

    @Override
    public Page<MutualFundOrder> getAllMutualFundBySipStatus(UUID userId, Pageable pageable) {
        Page<MutualFundOrder> pageResult = mutualFundOrderRepository.findAllBySipStatusNotAndInvestmentTypeNotAndInvestmentTypeNotAndAndUserIdOrderBySIPDateDesc( SIPStatus.DELETED , InvestmentType.ONE_TIME,InvestmentType.NONE,userId,pageable);
        return pageResult;
    }

    @Override
    public MutualFundOrder getMutualFundOrder(UUID orderId) {
        Optional<MutualFundOrder> mutualFundOrder = mutualFundOrderRepository.findById(orderId);
        return mutualFundOrder.isPresent() ? mutualFundOrder.get() : null;
    }

    public Page<StockTrade> getStockOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws Exception {

        DateFormat originalFormat = new SimpleDateFormat(dateFormat);
        DateFormat targetFormat = new SimpleDateFormat(dateFormatWithTime);
        Date sDate = originalFormat.parse(startDate);
        startDate = targetFormat.format(sDate);
        Date eDate = originalFormat.parse(endDate);
        endDate = targetFormat.format(eDate);
        sDate = targetFormat.parse(startDate);
        eDate = targetFormat.parse(endDate);
        if(sDate.compareTo(eDate) <= 1){
            Calendar cal = Calendar.getInstance();
            cal.setTime(eDate);
            cal.add(Calendar.DATE, 1);
            eDate = cal.getTime();
        }
        return stockTradeRepository.findAllByUserIdAndTimestampBetween(userId,sDate,eDate,pageable);
    }

    public MutualFundOrder updateMutualFundOrder(UUID id,MutualFundOrder newMutualFundOrder) {
        Optional<MutualFundOrder> isMutualFundOrder = mutualFundOrderRepository.findById(id);
        MutualFundOrder oldMutualFundOrder = isMutualFundOrder.get();
        oldMutualFundOrder.setPrice(newMutualFundOrder.getPrice());
        oldMutualFundOrder.setSipStatus(newMutualFundOrder.getSipStatus());
        oldMutualFundOrder.setSIPDate(newMutualFundOrder.getSIPDate());
        oldMutualFundOrder.setInvestmentType(newMutualFundOrder.getInvestmentType());
        mutualFundOrderRepository.save(oldMutualFundOrder);
        return oldMutualFundOrder;
    }

    public Page<MutualFundOrder> getMutualFundOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws ParseException {
        DateFormat originalFormat = new SimpleDateFormat(dateFormat);
        DateFormat targetFormat = new SimpleDateFormat(dateFormatWithTime);
        Date sDate = originalFormat.parse(startDate);
        startDate = targetFormat.format(sDate);
        Date eDate = originalFormat.parse(endDate);
        endDate = targetFormat.format(eDate);
        sDate = targetFormat.parse(startDate);
        eDate = targetFormat.parse(endDate);
        if(sDate.compareTo(eDate) <= 1){
            Calendar cal = Calendar.getInstance();
            cal.setTime(eDate);
            cal.add(Calendar.DATE, 1);
            eDate = cal.getTime();
        }
        return mutualFundOrderRepository.findAllByUserIdAndTimestampBetween(userId,sDate,eDate,pageable);
    }

    public void deleteMutualFundBySipStatus(UUID mutualFundOrderId) {
        mutualFundOrderRepository.deleteById(mutualFundOrderId);
    }
}
