package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.SIPStatus;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.exception.InsufficientStockException;
import com.pirimidtech.ptp.exception.NotFoundException;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import com.pirimidtech.ptp.service.trade.OrderService;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import com.pirimidtech.ptp.service.user.UserService;
import com.pirimidtech.ptp.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@RestController
public class OrderController {

    @Autowired
    private UserService userService;

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

    @Autowired
    private RequestUtil requestUtil;

    @PostMapping("/stock/orders")
    public ResponseEntity<StockTrade> addToStockOrder(HttpServletRequest httpServletRequest, @RequestBody StockTrade stockTrade) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        User user = new User();
        user.setId(userId);
        stockTrade.setUser(user);
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

    @GetMapping("/stock/orders")
    public ResponseEntity<Page<StockTrade>> getAllStockOrderByUser(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Page<StockTrade> stockTradeList = orderService.getAllStockOrder(userId, page, size);
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

    @GetMapping("/stock/orders/filter-by-date")
    public ResponseEntity<Page<StockTrade>> getStockOrderBasedOnDate(HttpServletRequest httpServletRequest,
                                                                     @RequestParam String startDate,
                                                                     @RequestParam String endDate,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) throws Exception {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Pageable pageable = PageRequest.of(page, size);
        Page<StockTrade> stockTradeBasedOnDate = orderService.getStockOrderFilteredOnDate(userId, startDate, endDate, pageable);
        return ResponseEntity.ok().body(stockTradeBasedOnDate);
    }

    @GetMapping("/mutualfund/orders/filter-by-date")
    public ResponseEntity<Page<MutualFundOrder>> getMutualFundOrderBasedOnDate(HttpServletRequest httpServletRequest,
                                                                               @RequestParam String startDate,
                                                                               @RequestParam String endDate,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size) throws Exception {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Pageable pageable = PageRequest.of(page, size);
        Page<MutualFundOrder> MutualFundTradeBasedOnDate = orderService.getMutualFundOrderFilteredOnDate(userId, startDate, endDate, pageable);
        return ResponseEntity.ok().body(MutualFundTradeBasedOnDate);
    }

    @PostMapping("/mutualfund/orders")
    public ResponseEntity<MutualFundOrder> addToMutualFundOrder(HttpServletRequest httpServletRequest, @RequestBody MutualFundOrder mutualFundOrder) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        User user = new User();
        user.setId(userId);
        mutualFundOrder.setUser(user);
        mutualFundOrder.setStatus(Status.PENDING);
        mutualFundOrder.setSipStatus(SIPStatus.ACTIVE);
        mutualFundOrder.setTimestamp(new Date());
        MutualFundStatistic mutualFundStatistic = mutualFundStatisticRepository.findById(mutualFundOrder.getMutualFundDetail().getId()).get();
        mutualFundOrder.setNav(mutualFundStatistic.getNav());
        mutualFundOrder = orderService.addToMutualFundOrder(mutualFundOrder);
        return ResponseEntity.ok().body(mutualFundOrder);
    }

    @PutMapping("/mutualfund/{id}")
    public ResponseEntity<MutualFundOrder> updateMutualFundOrder(HttpServletRequest httpServletRequest,
                                                                 @PathVariable UUID id,
                                                                 @RequestBody MutualFundOrder newMutualFundOrder) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Optional<User> user = userService.getUserById(userId);
        newMutualFundOrder.setUser(user.get());
        MutualFundOrder mutualFundOrder = orderService.updateMutualFundOrder(id, newMutualFundOrder);
        return ResponseEntity.ok().body(mutualFundOrder);
    }

    @GetMapping("/mutualfund/sip-status/users")
    public ResponseEntity<Page<MutualFundOrder>> getAllMutualFundSipStatusByUser(HttpServletRequest httpServletRequest,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        Pageable pageable = PageRequest.of(page, size);
        Page<MutualFundOrder> mutualFundOrderList = orderService.getAllMutualFundBySipStatus(userId, pageable);
        return ResponseEntity.ok().body(mutualFundOrderList);
    }

    @GetMapping("/mutualfund/sip-status-records/users")
    public int getTotalSipResponses(HttpServletRequest httpServletRequest){
        String jwtToken = requestUtil.getUserIdFromCookies(httpServletRequest);
        UUID userId = requestUtil.getUserIdFromToken(jwtToken);
        int totalSips = orderService.getTotalSips(userId);
        return totalSips;
    }

    @DeleteMapping("/mutualfund/delete-sip-status/users")
    public ResponseEntity<UUID> deleteMutualFundSipStatusByUser(@RequestParam UUID mutualFundOrderId) {
        orderService.deleteMutualFundBySipStatus(mutualFundOrderId);
        return ResponseEntity.ok().body(mutualFundOrderId);
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
