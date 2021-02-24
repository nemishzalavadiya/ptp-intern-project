package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.exception.InsufficientStockException;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.pirimidtech.ptp.repository.StockDetailRepository;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;

    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    @Autowired
    private StockDetailRepository stockDetailRepository;

    @PostMapping("/stock/orders")
    public ResponseEntity<StockTrade> addToStockOrder(@RequestBody StockTrade stockTrade) {
        if (stockTrade.getAction().equals(Action.SELL)) {
            Optional<StockDetail> stockDetail = stockDetailRepository.findById(stockTrade.getStockDetail().getId());
            AssetDetail assetDetail = stockDetail.get().getAssetDetail();
            Position position = positionService.getPositionByUserIdAndAssetDetailId(stockTrade.getUser().getId(), assetDetail.getId());
            if (position == null || position.getVolume() < stockTrade.getTradeVolume()) {
                throw new InsufficientStockException("insufficient stock");
            }
        }
        stockTrade.setStatus(Status.PENDING);
        stockTrade = orderService.addToStockOrder(stockTrade);
        StockTradeHistory stockTradeHistory = new StockTradeHistory(null, new Date(), Status.PENDING, stockTrade);
        stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        return ResponseEntity.ok().body(stockTrade);
    }

    @GetMapping("/stock/orders/users/{id}")
    public ResponseEntity<List<StockTrade>> getAllStockOrderByUser(@PathVariable("id") UUID userId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<StockTrade> stockTradeList = orderService.getAllStockOrder(userId, page, size);
        return ResponseEntity.ok().body(stockTradeList);
    }

    @GetMapping("/stock/orders/{id}")
    public ResponseEntity<StockTrade> getStockOrder(@PathVariable("id") UUID orderId) {
        StockTrade stockTrade = orderService.getStockOrder(orderId);
        if (stockTrade == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(stockTrade);

    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<MutualFundOrder> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder) {
        mutualFundOrder.setStatus(Status.PENDING);
        mutualFundOrder = orderService.addToMutualFundOrder(mutualFundOrder);
        return ResponseEntity.ok().body(mutualFundOrder);
    }

    @GetMapping("/mutualfund/orders/users/{id}")
    public ResponseEntity<List<MutualFundOrder>> getAllMutualFundOrderByUser(@PathVariable("id") UUID userId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<MutualFundOrder> mutualFundOrderList = orderService.getAllMutualFundOrder(userId, page, size);
        return ResponseEntity.ok().body(mutualFundOrderList);
    }

    @GetMapping("/mutualfund/orders/{id}")
    public ResponseEntity<MutualFundOrder> getMutualFundOrder(@PathVariable("id") UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        if (mutualFundOrder == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok().body(mutualFundOrder);
    }
}
