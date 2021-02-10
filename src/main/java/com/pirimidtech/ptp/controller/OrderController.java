package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.position.PositionService;
import com.pirimidtech.ptp.service.trade.OrderService;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;

    @Autowired
    private PositionService positionService;

    @PostMapping("/stock/orders")
    public ResponseEntity<UUID> addToStockOrder(@RequestBody StockTrade stockTrade) {
        UUID uuid;
        try {
            uuid = UUID.randomUUID();
            stockTrade.setId(uuid);
            stockTrade.setStatus(Status.PENDING);
//            stockTrade.setTimestamp(LocalDateTime.now());
            orderService.addToStockOrder(stockTrade);
            stockTradeHistoryService.addToStockTradeHistory(new StockTradeHistory(UUID.randomUUID(), LocalDateTime.now(), Status.PENDING, stockTrade));
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }

        return ResponseEntity.ok().body(uuid);
    }

    @GetMapping("/stock/orders/users/{id}")
    public ResponseEntity<List<StockTrade>> getAllStockOrder(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockTrade> stockTradeList = new ArrayList<>();
        try {
            stockTradeList = orderService.getAllStockOrder(userId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTradeList.size() != 0 ? ResponseEntity.ok().body(stockTradeList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stock/orders/{id}")
    public ResponseEntity<StockTrade> getStockOrder(@PathVariable("id") UUID orderId) {
        StockTrade stockTrade;
        try {
            stockTrade = orderService.getStockOrder(orderId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return stockTrade != null ? ResponseEntity.ok().body(stockTrade) : ResponseEntity.notFound().build();
    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<UUID> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder) {
        UUID uuid = null;
        try {
            uuid = UUID.randomUUID();
            mutualFundOrder.setId(uuid);
            orderService.addToMutualFundOrder(mutualFundOrder);

            positionService.addToPosition(new Position(UUID.randomUUID(), 0, mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundOrder.getMutualFundDetail().getAssetDetail()), null);

        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return ResponseEntity.ok().body(uuid);
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
    public ResponseEntity<MutualFundOrder> getMutualFundOrder(@PathVariable("id") UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder;

        try {
            mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return mutualFundOrder != null ? ResponseEntity.ok().body(mutualFundOrder) : ResponseEntity.notFound().build();
    }
}
