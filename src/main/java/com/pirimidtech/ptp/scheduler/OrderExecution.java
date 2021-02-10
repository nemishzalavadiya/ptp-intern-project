package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import com.pirimidtech.ptp.service.trade.OrderService;
import com.pirimidtech.ptp.service.tradeHistory.StockTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class OrderExecution {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    @Scheduled(fixedDelay = 10000)
    public void trigger()
    {
            stockTradeRepository.findAllByStatus(Status.PENDING).stream().forEach(
                    stockTrade -> {
                        stockTrade.setStatus(Status.EXECUTED);
                        stockTradeRepository.save(stockTrade);
                        StockTradeHistory stockTradeHistory=new StockTradeHistory(UUID.randomUUID(), LocalDateTime.now(),stockTrade.getStatus(),stockTrade);
                        stockTradeHistoryRepository.save(stockTradeHistory);
                        positionService.addToPosition(new Position(UUID.randomUUID(), stockTrade.getTradeVolume(),0f, AssetClass.STOCK, stockTrade.getUser(),stockTrade.getStockDetail().getAssetDetail()),stockTrade.getAction());
                    }
            );

    }

}
