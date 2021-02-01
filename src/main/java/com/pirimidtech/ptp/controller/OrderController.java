package com.pirimidtech.ptp.controller;


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

    @Autowired
    UserRepository userRepository;

    @PostMapping("/stock")
    public void addToStockOrder(@RequestBody StockOrder stockOrder)
    {
        orderService.addToStockOrder(stockOrder);
    }

    @GetMapping("/user")
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/stock/{orderId}")
    public List<StockOrder> getAllStockOrder(@PathVariable("orderId") UUID userId)
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


}
