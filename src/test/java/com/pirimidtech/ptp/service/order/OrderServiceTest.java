package com.pirimidtech.ptp.service.order;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.OrderType;
import com.pirimidtech.ptp.entity.PriceType;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.StockOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private StockOrderRepository stockOrderRepository;

    @MockBean
    private MutualFundOrderRepository mutualFundOrderRepository;

    User user=new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"abc","abc@dev.com","","","","", Gender.MALE,"");


    @Test
    void addToStockOrder() {
        StockOrder stockOrder=new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100, Action.BUY, StockExchangeType.BSE, PriceType.LIMIT, OrderType.DELIVERY,100f,"Active",null,null);
        orderService.addToStockOrder(stockOrder);
        verify(stockOrderRepository,times(1)).save(stockOrder);
    }

    @Test
    void getAllStockOrder() {
        List<StockOrder> stockOrderList=new ArrayList<>();
        stockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100, Action.BUY, StockExchangeType.BSE, PriceType.LIMIT, OrderType.DELIVERY,100f,"Active",user,null));
        stockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),200, Action.BUY, StockExchangeType.BSE, PriceType.MARKET, OrderType.INTRA_DAY,105f,"rejected",user,null));
        stockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),300, Action.BUY, StockExchangeType.BSE, PriceType.LIMIT, OrderType.DELIVERY,10086f,"Active",user,null));
        when(stockOrderRepository.findAllByUserId(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"))).thenReturn(stockOrderList);
        assertEquals(3,orderService.getAllStockOrder(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e")).size());
    }

    @Test
    void getStockOrder() {
        UUID orderId=UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e");
        StockOrder stockOrder=new StockOrder(orderId, LocalDateTime.now(),100, Action.BUY, StockExchangeType.BSE, PriceType.LIMIT, OrderType.DELIVERY,100f,"Active",user,null);
        when(stockOrderRepository.findById(orderId)).thenReturn(java.util.Optional.of(stockOrder));
        assertEquals(stockOrder,orderService.getStockOrder(orderId));
    }

    @Test
    void deleteStockOrder() {
        UUID orderId=UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e");
        orderService.deleteStockOrder(orderId);
        verify(stockOrderRepository,times(1)).deleteById(orderId);
    }

    @Test
    void addToMutualFundOrder() {
        MutualFundOrder mutualFundOrder=new MutualFundOrder(UUID.randomUUID(),LocalDateTime.now(),100f, InvestmentType.MONTHLY_SIP,null,user);
        orderService.addToMutualFundOrder(mutualFundOrder);
        verify(mutualFundOrderRepository,times(1)).save(mutualFundOrder);
    }

    @Test
    void getAllMutualFundOrder() {
        List<MutualFundOrder> mutualFundOrderList=new ArrayList<>();
        mutualFundOrderList.add(new MutualFundOrder(UUID.randomUUID(),null,100f,InvestmentType.ONE_TIME,null,user));
        mutualFundOrderList.add(new MutualFundOrder(UUID.randomUUID(),LocalDateTime.now(),100f,InvestmentType.MONTHLY_SIP,null,user));
        mutualFundOrderList.add(new MutualFundOrder(UUID.randomUUID(),LocalDateTime.now(),100f,InvestmentType.MONTHLY_SIP,null,user));
        when(mutualFundOrderRepository.findAllByUserId(user.getId())).thenReturn(mutualFundOrderList);
        assertEquals(3,orderService.getAllMutualFundOrder(user.getId()).size());
    }

    @Test
    void getMutualFundOrder() {
        UUID orderId=UUID.fromString("4ee43b74-5582-49cb-a5c0-ee1bf638b995");
        MutualFundOrder mutualFundOrder=new MutualFundOrder(orderId,null,100f,InvestmentType.ONE_TIME,null,user);
        when(mutualFundOrderRepository.findById(orderId)).thenReturn(java.util.Optional.of(mutualFundOrder));
        assertEquals(mutualFundOrder,orderService.getMutualFundOrder(orderId));
    }


    @Test
    void deleteMutualFundOrder() {
        UUID orderId=UUID.fromString("4ee43b74-5582-49cb-a5c0-ee1bf638b995");
        orderService.deleteMutualFundOrder(orderId);
        verify(mutualFundOrderRepository,times(1)).deleteById(orderId);
    }
}