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

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Override
    public StockTrade addToStockOrder(StockTrade stockTrade) {
        return stockTradeRepository.save(stockTrade);
    }

    @Override
    public List<StockTrade> getAllStockOrder(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StockTrade> pageResult= stockTradeRepository.findAllByUserIdOrderByTimestampDesc(userId,pageable);

        return pageResult.toList();
    }

    @Override
    public StockTrade getStockOrder(UUID orderId) {
        Optional<StockTrade> stockOrder= stockTradeRepository.findById(orderId);
        return stockOrder.isPresent()?stockOrder.get():null;
    }

    @Override
    public MutualFundOrder addToMutualFundOrder(MutualFundOrder mutualFundOrder) {
        return mutualFundOrderRepository.save(mutualFundOrder);
    }

    @Override
    public List<MutualFundOrder> getAllMutualFundOrder(UUID userId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MutualFundOrder> pageResult=mutualFundOrderRepository.findAllByUserIdOrderBySIPDateDesc(userId,pageable);

        return pageResult.toList();
    }

    @Override
    public MutualFundOrder getMutualFundOrder(UUID orderId) {
        Optional<MutualFundOrder> mutualFundOrder =mutualFundOrderRepository.findById(orderId);
        return mutualFundOrder.isPresent()?mutualFundOrder.get():null;
    }
}
