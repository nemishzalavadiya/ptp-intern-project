package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
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

import java.time.LocalDate;
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

    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;

    @Autowired
    private StockDetailRepository stockDetailRepository;

    @PostMapping("/stock/orders")
    public ResponseEntity<StockTrade> addToStockOrder(@RequestBody StockTrade stockTrade) {
        stockTrade.setId(null);
        if (stockTrade.getAction().equals(Action.SELL)) {
            Position position = positionService.getPositionByUserIdAndAssetDetailId(stockTrade.getUser().getId(), stockDetailRepository.findById(stockTrade.getStockDetail().getId()).get().getAssetDetail().getId());
            if (position == null || position.getVolume() < stockTrade.getTradeVolume()) {
                throw new NotFoundException("insufficient stock");
            }
        }
        stockTrade.setStatus(Status.PENDING);
        stockTrade.setTimestamp(LocalDateTime.now());
        stockTrade=orderService.addToStockOrder(stockTrade);
        stockTradeHistoryService.addToStockTradeHistory(new StockTradeHistory(null, LocalDateTime.now(), Status.PENDING, stockTrade));
        return ResponseEntity.ok().body(stockTrade);
    }

    @GetMapping("/stock/orders/users/{id}")
    public ResponseEntity<List<StockTrade>> getAllStockOrder(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<StockTrade> stockTradeList = orderService.getAllStockOrder(userId, page, size);
        return stockTradeList.size() != 0 ? ResponseEntity.ok().body(stockTradeList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stock/orders/{id}")
    public ResponseEntity<StockTrade> getStockOrder(@PathVariable("id") UUID orderId) {
        StockTrade stockTrade = orderService.getStockOrder(orderId);
        return stockTrade != null ? ResponseEntity.ok().body(stockTrade) : ResponseEntity.notFound().build();
    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<MutualFundOrder> addToMutualFundOrder(@RequestBody MutualFundOrder mutualFundOrder) {
        if (mutualFundOrder.getInvestmentType().equals(InvestmentType.MONTHLY_SIP)) {
            mutualFundOrder.setSIPDate(LocalDate.now());
        } else if (mutualFundOrder.getInvestmentType().equals(InvestmentType.ONE_TIME)) {
            mutualFundOrder.setSIPDate(null);
        }
        mutualFundOrder=orderService.addToMutualFundOrder(mutualFundOrder);
        positionService.addToPosition(new Position(null, 0, mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundDetailRepository.findById(mutualFundOrder.getMutualFundDetail().getId()).get().getAssetDetail()), Action.BUY);
        return ResponseEntity.ok().body(mutualFundOrder);
    }

    @GetMapping("/mutualfund/orders/users/{id}")
    public ResponseEntity<List<MutualFundOrder>> getAllMutualFundOrder(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<MutualFundOrder> mutualFundOrderList;
        mutualFundOrderList = orderService.getAllMutualFundOrder(userId, page, size);
        return mutualFundOrderList.size() != 0 ? ResponseEntity.ok().body(mutualFundOrderList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/mutualfund/orders/{id}")
    public ResponseEntity<MutualFundOrder> getMutualFundOrder(@PathVariable("id") UUID mutualFundOrderId) {
        MutualFundOrder mutualFundOrder;
        mutualFundOrder = orderService.getMutualFundOrder(mutualFundOrderId);
        return mutualFundOrder != null ? ResponseEntity.ok().body(mutualFundOrder) : ResponseEntity.notFound().build();
    }
}
