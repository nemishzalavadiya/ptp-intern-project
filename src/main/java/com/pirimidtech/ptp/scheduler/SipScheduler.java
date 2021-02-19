package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class SipScheduler {

    @Autowired
    PositionService positionService;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Autowired
    private MutualFundPriceRepository mutualFundPriceRepository;
    @Scheduled(cron = "0 0 10 * * *")
    public void trigger() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int yearDay = calendar.get(Calendar.DAY_OF_YEAR);
        List<MutualFundOrder> mutualFundOrderList = mutualFundOrderRepository.findAllByUserIdOrderByDay(day);
        List<MutualFundOrder> mutualFundOrderListByWeekDay = mutualFundOrderRepository.findAllByUserIdOrderByWeekDay(weekDay);
        List<MutualFundOrder> mutualFundOrderListByYearDay = mutualFundOrderRepository.findAllByUserIdOrderByYearDay(yearDay);

        mutualFundOrderListByWeekDay.forEach(mutualFundOrder -> {
            MutualFundPrice mutualFundPrice = mutualFundPriceRepository.findFirstByMutualFundDetailId(mutualFundOrder.getMutualFundDetail().getId());
            Position position = new Position(null,mutualFundOrder.getPrice()/mutualFundPrice.getPrice(), mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundOrder.getMutualFundDetail().getAssetDetail());
            positionService.addMutualFundToPosition(position);

        });
        mutualFundOrderList.forEach(mutualFundOrder -> {
                    MutualFundPrice mutualFundPrice = mutualFundPriceRepository.findFirstByMutualFundDetailId(mutualFundOrder.getMutualFundDetail().getId());
                    Position position = new Position(null,mutualFundOrder.getPrice()/mutualFundPrice.getPrice(), mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundOrder.getMutualFundDetail().getAssetDetail());
                    positionService.addMutualFundToPosition(position);
                }
        );
        mutualFundOrderListByYearDay.forEach(mutualFundOrder -> {
            MutualFundPrice mutualFundPrice = mutualFundPriceRepository.findFirstByMutualFundDetailId(mutualFundOrder.getMutualFundDetail().getId());
            Position position = new Position(null,mutualFundOrder.getPrice()/mutualFundPrice.getPrice(), mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundOrder.getMutualFundDetail().getAssetDetail());
            positionService.addMutualFundToPosition(position);

        });

    }
}
