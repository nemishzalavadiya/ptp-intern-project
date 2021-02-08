package com.pirimidtech.ptp.service.order;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockOrderRepository;
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
    private StockOrderRepository stockOrderRepository;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Override
    public void addToStockOrder(StockOrder stockOrder) {
        stockOrderRepository.save(stockOrder);
    }

    @Override
    public List<StockOrder> getAllStockOrder(UUID userId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StockOrder> pageResult=stockOrderRepository.findAllByUserId(userId,pageable);

        return pageResult.toList();
    }

    @Override
    public StockOrder getStockOrder(UUID orderId) {
        Optional<StockOrder> stockOrder=stockOrderRepository.findById(orderId);
        return stockOrder.isPresent()?stockOrder.get():null;
    }

    @Override
    public void deleteStockOrder(UUID orderId) {
        stockOrderRepository.deleteById(orderId);
    }

    @Override
    public void addToMutualFundOrder(MutualFundOrder mutualFundOrder) {
        mutualFundOrderRepository.save(mutualFundOrder);
    }

    @Override
    public List<MutualFundOrder> getAllMutualFundOrder(UUID userId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MutualFundOrder> pageResult=mutualFundOrderRepository.findAllByUserId(userId,pageable);

        return pageResult.toList();
    }

    @Override
    public MutualFundOrder getMutualFundOrder(UUID orderId) {
        Optional<MutualFundOrder> mutualFundOrder =mutualFundOrderRepository.findById(orderId);
        return mutualFundOrder.isPresent()?mutualFundOrder.get():null;
    }

    @Override
    public void deleteMutualFundOrder(UUID mutualFundOrderId) {
        mutualFundOrderRepository.deleteById(mutualFundOrderId);
    }
}
