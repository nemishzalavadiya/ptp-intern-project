package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/stock/orders")
    public ResponseEntity<Void> addToStockOrder(@RequestBody StockOrder stockOrder)
    {
        try {
            orderService.addToStockOrder(stockOrder);
        }
        catch (Exception exception){
            throw new ErrorHandler(exception.getCause());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/stock/orders/users/{id}")
    public List<StockOrder> getAllStockOrder(@PathVariable("id") UUID userId)
    {
        List<StockOrder> stockOrderList = new ArrayList<>();
        try {
            stockOrderList = orderService.getAllStockOrder(userId);
        }
        catch(Exception exception)
        {
            throw new ErrorHandler(exception.getCause());
        }
        return stockOrderList;
    }

    @GetMapping("/stock/orders/{id}")
    public StockOrder getStockOrder(@PathVariable("id") UUID orderId)
    {
        StockOrder stockOrder = new StockOrder();
        try {
            stockOrder = orderService.getStockOrder(orderId);
        }
        catch (Exception exception)
        {
            throw new ErrorHandler(exception.getCause());
        }
        return stockOrder;
    }

    @PutMapping("/stock/orders/{id}")
    public ResponseEntity<Void> updateStockOrder(@PathVariable("id") UUID orderId,@RequestBody StockOrder stockOrder)
    {
        try {
            stockOrder.setId(orderId);
            orderService.addToStockOrder(stockOrder);
        }
        catch(Exception exception)
        {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stock/orders/{id}")
    public ResponseEntity<Void> deleteStockOrder(@PathVariable("id") UUID orderId)
    {
        try{
        orderService.deleteStockOrder(orderId);
        }
        catch (Exception exception)
        {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<Void> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder){
        try{
        orderService.addToMutualFundOrder(mutualFundOrder);}
        catch (Exception exception){
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mutualfund/orders/{id}")
    public ResponseEntity<Void> updateMutualFundOrder(@PathVariable("id") UUID orderId,@RequestBody MutualFundOrder mutualFundOrder){
        try {
            mutualFundOrder.setId(orderId);
            orderService.addToMutualFundOrder(mutualFundOrder);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mutualfund/orders/users/{id}")
    public List<MutualFundOrder> getAllMutualFundOrder(@PathVariable("id") UUID userId) {
        List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();

        try {
            mutualFundOrderList = orderService.getAllMutualFundOrder(userId);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }

        return mutualFundOrderList;
    }

    @GetMapping("/mutualfund/orders/{id}")
    public MutualFundOrder getMutualFundOrderOrder(@PathVariable("id") UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder = new MutualFundOrder();

        try {
            mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundOrder;
    }

    @DeleteMapping("/mutualfund/orders/{id}")
    public ResponseEntity<Void> deleteMutualFundOrder(@PathVariable("id") UUID mutualFundOrderId) {
        try {
            orderService.deleteMutualFundOrder(mutualFundOrderId);
        } catch (Exception exception) {
            throw new ErrorHandler("Mutualfund with given Id is not exist",exception.getCause());
        }
        return ResponseEntity.ok().build();
    }
}
