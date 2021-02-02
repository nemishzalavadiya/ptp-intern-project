package com.pirimidtech.ptp.controller;


import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.repository.UserRepository;
import com.pirimidtech.ptp.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/stock")
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

    @GetMapping("/stock/{userId}")
    public List<StockOrder> getAllStockOrder(@PathVariable("userId") UUID userId)
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

    @GetMapping("/stock/order/{orderId}")
    public StockOrder getStockOrder(@PathVariable("orderId") UUID orderId)
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

    @PutMapping("/stock/update/{orderId}")
    public ResponseEntity<Void> updateStockOrder(@PathVariable("orderId") UUID orderId,@RequestBody StockOrder stockOrder)
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

    @DeleteMapping("/stock/delete/{orderId}")
    public ResponseEntity<Void> deleteStockOrder(@PathVariable("orderId") UUID orderId)
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

    @PostMapping("/mutualFund")
    public ResponseEntity<Void> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder){
        try{
        orderService.addToMutualFundOrder(mutualFundOrder);}
        catch (Exception exception){
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mutualFund/update/{mutualFundId}")
    public ResponseEntity<Void> updateMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder){
        try {
            orderService.addToMutualFundOrder(mutualFundOrder);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mutualFund/{userId}")
    public List<MutualFundOrder> getAllMutualFundOrder(@PathVariable UUID userId) {
        List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();

        try {
            mutualFundOrderList = orderService.getAllMutualFundOrder(userId);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }

        return mutualFundOrderList;
    }

    @GetMapping("/mutualFund/order/{mutualFundOrderId}")
    public MutualFundOrder getMutualFundOrderOrder(@PathVariable UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder = new MutualFundOrder();

        try {
            mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        }
        catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundOrder;
    }

    @DeleteMapping("/mutualFund/{mutualFundOrderId}")
    public ResponseEntity<Void> deleteMutualFundOrder(@PathVariable UUID mutualFundOrderId) {
        try {
            orderService.deleteMutualFundOrder(mutualFundOrderId);
        } catch (Exception exception) {
            throw new ErrorHandler("Mutualfund with given Id is not exist",exception.getCause());
        }
        return ResponseEntity.ok().build();
    }
}
