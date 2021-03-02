package com.pirimidtech.ptp.scheduler;


import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.Status;


import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class SipScheduler {

    @Autowired
    PositionService positionService;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;

    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    @Scheduled(cron = "0 0 16 * * *")
    public void trigger() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int yearDay = calendar.get(Calendar.DAY_OF_YEAR);
        List<MutualFundOrder> mutualFundOrderListByMonthDay = mutualFundOrderRepository.findAllByUserIdOrderByDay(day);
        List<MutualFundOrder> mutualFundOrderListByWeekDay = mutualFundOrderRepository.findAllByUserIdOrderByWeekDay(weekDay);
        List<MutualFundOrder> mutualFundOrderListByYearDay = mutualFundOrderRepository.findAllByUserIdOrderByYearDay(yearDay);

        mutualFundOrderListByWeekDay.forEach(mutualFundOrder -> {
            if (mutualFundOrder.getStatus() == Status.PENDING) {
                addToMutualFundOrder(mutualFundOrder);
            } else {
                addToMutualFundOrderRepeat(mutualFundOrder);
            }
        });
        mutualFundOrderListByMonthDay.forEach(mutualFundOrder -> {
                    if (mutualFundOrder.getStatus() == Status.PENDING) {
                        addToMutualFundOrder(mutualFundOrder);
                    } else {
                        addToMutualFundOrderRepeat(mutualFundOrder);
                    }
                }
        );
        mutualFundOrderListByYearDay.forEach(mutualFundOrder -> {
            if (mutualFundOrder.getStatus() == Status.PENDING) {
                addToMutualFundOrder(mutualFundOrder);
            } else {
                addToMutualFundOrderRepeat(mutualFundOrder);
            }
        });

    }

    void addToMutualFundOrder(MutualFundOrder mutualFundOrder) {
        mutualFundOrder.setStatus(Status.EXECUTED);
        MutualFundStatistic mutualFundStatistic = mutualFundStatisticRepository.findById(mutualFundOrder.getMutualFundDetail().getId()).get();
        mutualFundOrder.setNav(mutualFundStatistic.getNav());
        mutualFundOrderRepository.save(mutualFundOrder);
        Optional<MutualFundDetail> mutualFundDetail = mutualFundDetailRepository.findById(mutualFundOrder.getMutualFundDetail().getId());
        AssetDetail assetDetail = mutualFundDetail.get().getAssetDetail();
        Position position = new Position(null, mutualFundOrder.getPrice() / mutualFundStatistic.getNav(), mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), assetDetail);
        positionService.addMutualFundToPosition(position);

    }

    void addToMutualFundOrderRepeat(MutualFundOrder mutualFundOrder) {
        MutualFundStatistic mutualFundStatistic = mutualFundStatisticRepository.findById(mutualFundOrder.getMutualFundDetail().getId()).get();
        MutualFundOrder mutualFundOrderRepeat = mutualFundOrder;
        mutualFundOrderRepeat.setId(null);
        mutualFundOrderRepeat.setStatus(Status.PENDING);
        mutualFundOrderRepeat.setInvestmentType(InvestmentType.NONE);
        mutualFundOrder.setNav(mutualFundStatistic.getNav());
        mutualFundOrderRepository.save(mutualFundOrderRepeat);
    }
}
