package com.pirimidtech.ptp.service.trade;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {

    User user = ObjectUtility.user;
    @Autowired
    private OrderService orderService;
    @MockBean
    private StockTradeRepository stockTradeRepository;
    @MockBean
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Test
    void addToStockOrder() {
        StockTrade stockTrade = ObjectUtility.stockTrade1;
        orderService.addToStockOrder(stockTrade);
        verify(stockTradeRepository, times(1)).save(stockTrade);
    }

    @Test
    void getAllStockOrder() {
        List<StockTrade> stockTradeList = new ArrayList<>();
        stockTradeList.add(ObjectUtility.stockTrade1);
        stockTradeList.add(ObjectUtility.stockTrade2);
        when(stockTradeRepository.findAllByUserIdOrderByTimestampDesc(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<StockTrade>(stockTradeList));
        assertEquals(2, orderService.getAllStockOrder(user.getId(), 0, 2).size());
    }

    @Test
    void getStockOrder() {
        StockTrade stockTrade = ObjectUtility.stockTrade1;
        when(stockTradeRepository.findById(stockTrade.getId())).thenReturn(java.util.Optional.of(stockTrade));
        assertEquals(stockTrade, orderService.getStockOrder(stockTrade.getId()));
    }

    @Test
    void addToMutualFundOrder() {
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        orderService.addToMutualFundOrder(mutualFundOrder);
        verify(mutualFundOrderRepository, times(1)).save(mutualFundOrder);
    }

    @Test
    void getAllMutualFundOrder() {
        List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();
        mutualFundOrderList.add(ObjectUtility.mutualFundOrder1);
        mutualFundOrderList.add(ObjectUtility.mutualFundOrder2);
        when(mutualFundOrderRepository.findAllByUserIdOrderBySIPDateDesc(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<MutualFundOrder>(mutualFundOrderList));
        assertEquals(2, orderService.getAllMutualFundOrder(user.getId(), 0, 2).size());
    }

    @Test
    void getMutualFundOrder() {
        UUID orderId = ObjectUtility.mutualFundOrder1.getId();
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        when(mutualFundOrderRepository.findById(orderId)).thenReturn(java.util.Optional.of(mutualFundOrder));
        assertEquals(mutualFundOrder, orderService.getMutualFundOrder(orderId));
    }

}