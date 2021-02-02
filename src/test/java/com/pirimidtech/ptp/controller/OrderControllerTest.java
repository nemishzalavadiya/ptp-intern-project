//package com.pirimidtech.ptp.controller;
//
//import com.pirimidtech.ptp.entity.*;
//import com.pirimidtech.ptp.repository.StockOrderRepository;
//import com.pirimidtech.ptp.repository.UserRepository;
//import com.pirimidtech.ptp.service.order.OrderService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class OrderControllerTest {
//
//    PodamFactory factory = new PodamFactoryImpl();
//
//    @InjectMocks
//    OrderService orderService;
//
//    @Mock
//    StockOrderRepository stockOrderRepository;
//
//    @Mock
//    MutualFundOrder mutualFundOrder;
//
//    @Test
//    void addToStockOrder() {
//        StockOrder stockOrder = new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100,Action.BUY,StockExchangeType.BSE, PriceType.MARKET,OrderType.DELIVERY, (float)Math.random(),"EXECUTED",new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"),"hash"," "," "," "," "," ", Gender.MALE,"Hash"),factory.manufacturePojo(StockDetail.class));
//        System.out.println((stockOrder.toString()));
//    }
//
//    @Test
//    void getAllStockOrder() {
//        List<StockOrder> StockOrderList =  new ArrayList<>();
//        StockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100,Action.BUY,StockExchangeType.BSE, PriceType.MARKET,OrderType.DELIVERY, (float)Math.random(),"EXECUTED",new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"),"hash"," "," "," "," "," ", Gender.MALE,"Hash"),new StockDetail()));
//        StockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100,Action.BUY,StockExchangeType.BSE, PriceType.MARKET,OrderType.DELIVERY, (float)Math.random(),"EXECUTED",new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"),"hash"," "," "," "," "," ", Gender.MALE,"Hash"),new StockDetail()));
//        StockOrderList.add(new StockOrder(UUID.randomUUID(), LocalDateTime.now(),100,Action.BUY,StockExchangeType.BSE, PriceType.MARKET,OrderType.DELIVERY, (float)Math.random(),"EXECUTED",new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"),"hash"," "," "," "," "," ", Gender.MALE,"Hash"),new StockDetail()));
//
//        when(stockOrderRepository.findAllByUserId(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"))).thenReturn(StockOrderList);
//        List<StockOrder> StockOrderList2 = orderService.getAllStockOrder(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"));
//        System.out.println("---------------------------------------"+StockOrderList.size());
////        assertEquals(StockOrderList2.size(),StockOrderList.size());
//        verify(1);
//    }
//
//    @Test
//    void getStockOrder() {
//    }
//
//    @Test
//    void updateStockOrder() {
//    }
//
//    @Test
//    void deleteStockOrder() {
//    }
//}