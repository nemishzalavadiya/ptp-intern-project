package com.pirimidtech.ptp.controller;


import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.UserRepository;
import com.pirimidtech.ptp.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/stock")
    public void addToStockOrder(@RequestBody StockOrder stockOrder)
    {
        orderService.addToStockOrder(stockOrder);
    }

    @GetMapping("/stock/{userId}")
    public List<StockOrder> getAllStockOrder(@PathVariable("userId") UUID userId)
    {
        return orderService.getAllStockOrder(userId);
    }

    @GetMapping("/stock/order/{orderId}")
    public StockOrder getStockOrder(@PathVariable("orderId") UUID orderId)
    {
        return orderService.getStockOrder(orderId);
    }

    @PutMapping("/stock/update/{orderId}")
    public void updateStockOrder(@PathVariable("orderId") UUID orderId,@RequestBody StockOrder stockOrder)
    {
        stockOrder.setId(orderId);
        orderService.addToStockOrder(stockOrder);
    }
    @DeleteMapping("/stock/delete/{orderId}")
    public void deleteStockOrder(@PathVariable("orderId") UUID orderId)
    {
        orderService.deleteStockOrder(orderId);
    }


    @PostMapping("/mutualFund")
    void addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder){
        orderService.addToMutualFundOrder(mutualFundOrder);
    }

    @PutMapping("/mutualFund/update/{mutualFundId}")
    void updateMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder){
        orderService.addToMutualFundOrder(mutualFundOrder);
    }

    @GetMapping("/mutualFund/{userId}")
    public List<MutualFundOrder> getAllMutualFundOrder(@PathVariable UUID userId) {
        return orderService.getAllMutualFundOrder(userId);
    }

    @GetMapping("/mutualFund/order/{mutualFundOrderId}")
    public MutualFundOrder getMutualFundOrderOrder(@PathVariable UUID mutualFundOrderId) {
        return orderService.getMutualFundOrder(mutualFundOrderId);
    }

    @DeleteMapping("/mutualFund/{mutualFundOrderId}")
    public void deleteMutualFundOrder(@PathVariable UUID mutualFundOrderId) {
        orderService.deleteMutualFundOrder(mutualFundOrderId);
    }


}
