package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
=======
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
>>>>>>> 767e2bd (ignore)


@Service
public class OrderService implements OrderServiceInterface {

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
    public Page<MutualFundOrder> getAllMutualFundOrder(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MutualFundOrder> pageResult = mutualFundOrderRepository.findAllByUserIdOrderBySIPDateDesc(userId, pageable);
        return pageResult;
    }

    @Override
    public MutualFundOrder getMutualFundOrder(UUID orderId) {
        Optional<MutualFundOrder> mutualFundOrder = mutualFundOrderRepository.findById(orderId);
        return mutualFundOrder.isPresent() ? mutualFundOrder.get() : null;
    }

    public Page<StockTrade> getStockOrderFilteredOnDate(UUID userId, String startDate, String endDate, Pageable pageable) throws Exception {

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
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
}
