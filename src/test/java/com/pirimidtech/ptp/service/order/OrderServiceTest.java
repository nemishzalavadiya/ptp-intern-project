package com.pirimidtech.ptp.service.order;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockOrderRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    User user = ObjectUtility.user;
    @Autowired
    private OrderService orderService;
    @MockBean
    private StockOrderRepository stockOrderRepository;
    @MockBean
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Test
    void addToStockOrder() {
        StockOrder stockOrder = ObjectUtility.stockOrder1;
        orderService.addToStockOrder(stockOrder);
        verify(stockOrderRepository, times(1)).save(stockOrder);
    }

    @Test
    void getAllStockOrder() {
        List<StockOrder> stockOrderList = new ArrayList<>();
        stockOrderList.add(ObjectUtility.stockOrder1);
        stockOrderList.add(ObjectUtility.stockOrder2);

        when(stockOrderRepository.findAllByUserId(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<StockOrder>(stockOrderList));
        assertEquals(2, orderService.getAllStockOrder(user.getId(), 0, 2).size());
    }

    @Test
    void getStockOrder() {
        StockOrder stockOrder = ObjectUtility.stockOrder1;
        when(stockOrderRepository.findById(stockOrder.getId())).thenReturn(java.util.Optional.of(stockOrder));
        assertEquals(stockOrder, orderService.getStockOrder(stockOrder.getId()));
    }

    @Test
    void deleteStockOrder() {

        orderService.deleteStockOrder(ObjectUtility.stockOrder1.getId());
        verify(stockOrderRepository, times(1)).deleteById(ObjectUtility.stockOrder1.getId());
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
        when(mutualFundOrderRepository.findAllByUserId(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<MutualFundOrder>(mutualFundOrderList));
        assertEquals(2, orderService.getAllMutualFundOrder(user.getId(), 0, 2).size());
    }

    @Test
    void getMutualFundOrder() {
        UUID orderId = ObjectUtility.mutualFundOrder1.getId();
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        when(mutualFundOrderRepository.findById(orderId)).thenReturn(java.util.Optional.of(mutualFundOrder));
        assertEquals(mutualFundOrder, orderService.getMutualFundOrder(orderId));
    }


    @Test
    void deleteMutualFundOrder() {
        UUID orderId = ObjectUtility.mutualFundOrder1.getId();
        orderService.deleteMutualFundOrder(orderId);
        verify(mutualFundOrderRepository, times(1)).deleteById(orderId);
    }
}