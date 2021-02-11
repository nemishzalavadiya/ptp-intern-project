package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderExecution {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    @Scheduled(fixedDelay = 10000)
    public void trigger() {

        stockTradeRepository.findAllByStatus(Status.PENDING).stream().forEach(
                stockTrade -> {
                    stockTrade.setStatus(Status.EXECUTED);
                    stockTradeRepository.save(stockTrade);
                    StockTradeHistory stockTradeHistory = new StockTradeHistory(null, LocalDateTime.now(), stockTrade.getStatus(), stockTrade);
                    stockTradeHistoryRepository.save(stockTradeHistory);
                    positionService.addToPosition(new Position(null, stockTrade.getTradeVolume(), 0f, AssetClass.STOCK, stockTrade.getUser(), stockTrade.getStockDetail().getAssetDetail()), stockTrade.getAction());
                }
        );

    }

}
