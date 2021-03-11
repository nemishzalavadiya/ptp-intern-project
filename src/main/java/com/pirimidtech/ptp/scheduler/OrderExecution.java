package com.pirimidtech.ptp.scheduler;


import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import com.pirimidtech.ptp.repository.StockTradeRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Component
public class OrderExecution {

    @Autowired
    private StockTradeRepository stockTradeRepository;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;

    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    @Scheduled(fixedDelay = 20000)
    public void trigger() {

        stockTradeRepository.findAllByStatus(Status.PENDING).forEach(
                stockTrade -> {
                    stockTrade.setStatus(Status.EXECUTED);
                    stockTradeRepository.save(stockTrade);
                    StockTradeHistory stockTradeHistory = new StockTradeHistory(null, new Date(), stockTrade.getStatus(), stockTrade);
                    stockTradeHistoryRepository.save(stockTradeHistory);
                    Position position = new Position(null, stockTrade.getTradeVolume(), stockTrade.getPrice(), AssetClass.STOCK, stockTrade.getUser(), stockTrade.getStockDetail().getAssetDetail());
                    positionService.addStockToPosition(position, stockTrade.getAction());
                }
        );

        mutualFundOrderRepository.findAllByStatus(Status.PENDING).forEach(
                mutualFundOrder -> {
                    if (mutualFundOrder.getInvestmentType() == InvestmentType.ONE_TIME || mutualFundOrder.getInvestmentType() == InvestmentType.NONE) {
                        mutualFundOrder.setStatus(Status.EXECUTED);
                        Optional<MutualFundDetail> mutualFundDetail = mutualFundDetailRepository.findById(mutualFundOrder.getMutualFundDetail().getId());
                        AssetDetail assetDetail = mutualFundDetail.get().getAssetDetail();
                        MutualFundStatistic mutualFundStatistic = mutualFundStatisticRepository.findById(mutualFundOrder.getMutualFundDetail().getId()).get();
                        mutualFundOrderRepository.save(mutualFundOrder);
                        Position position = new Position(null, mutualFundOrder.getPrice() / mutualFundStatistic.getNav(), mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), assetDetail);
                        positionService.addMutualFundToPosition(position);
                    }
                }
        );

    }

}
