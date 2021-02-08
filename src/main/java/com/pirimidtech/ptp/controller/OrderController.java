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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/stock/orders")
    public ResponseEntity<UUID> addToStockOrder(@RequestBody StockOrder stockOrder) {
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            stockOrder.setId(uuid);
            orderService.addToStockOrder(stockOrder);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }

        return ResponseEntity.ok().body(uuid);
    }

    @GetMapping("/stock/orders/users/{id}")
    public ResponseEntity<List<StockOrder>> getAllStockOrder(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockOrder> stockOrderList = new ArrayList<>();
        try {
            stockOrderList = orderService.getAllStockOrder(userId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockOrderList.size() != 0 ? ResponseEntity.ok().body(stockOrderList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stock/orders/{id}")
    public ResponseEntity<StockOrder> getStockOrder(@PathVariable("id") UUID orderId) {
        StockOrder stockOrder;
        try {
            stockOrder = orderService.getStockOrder(orderId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockOrder != null ? ResponseEntity.ok().body(stockOrder) : ResponseEntity.notFound().build();
    }

    @PutMapping("/stock/orders/{id}")
    public ResponseEntity<Void> updateStockOrder(@PathVariable("id") UUID orderId, @RequestBody StockOrder stockOrder) {
        try {
            stockOrder.setId(orderId);
            orderService.addToStockOrder(stockOrder);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stock/orders/{id}")
    public ResponseEntity<Void> deleteStockOrder(@PathVariable("id") UUID orderId) {
        try {
            orderService.deleteStockOrder(orderId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<UUID> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder) {
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            mutualFundOrder.setId(uuid);
            orderService.addToMutualFundOrder(mutualFundOrder);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().body(uuid);
    }

    @PutMapping("/mutualfund/orders/{id}")
    public ResponseEntity<Void> updateMutualFundOrder(@PathVariable("id") UUID orderId, @RequestBody MutualFundOrder mutualFundOrder) {
        try {
            mutualFundOrder.setId(orderId);
            orderService.addToMutualFundOrder(mutualFundOrder);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mutualfund/orders/users/{id}")
    public ResponseEntity<List<MutualFundOrder>> getAllMutualFundOrder(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();

        try {
            mutualFundOrderList = orderService.getAllMutualFundOrder(userId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }

        return mutualFundOrderList.size() != 0 ? ResponseEntity.ok().body(mutualFundOrderList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/mutualfund/orders/{id}")
    public ResponseEntity<MutualFundOrder> getMutualFundOrderOrder(@PathVariable("id") UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder;

        try {
            mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundOrder != null ? ResponseEntity.ok().body(mutualFundOrder) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/mutualfund/orders/{id}")
    public ResponseEntity<Void> deleteMutualFundOrder(@PathVariable("id") UUID mutualFundOrderId) {
        try {
            orderService.deleteMutualFundOrder(mutualFundOrderId);
        } catch (Exception exception) {
            throw new ErrorHandler("Mutualfund with given Id is not exist", exception.getCause());
        }
        return ResponseEntity.ok().build();
    }
}
