package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class navScheduler {

    @Autowired
    private MutualFundPriceRepository mutualFundPriceRepository;

    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;

    @Scheduled(cron = "0 0 21 * * *")
    public void trigger() {
        List<MutualFundDetail> mutualFundDetailList = mutualFundDetailRepository.findAll();
        mutualFundDetailList.forEach(mutualFundDetail -> {
            MutualFundStatistic mutualFundStatistic = mutualFundStatisticRepository.findById(mutualFundDetail.getId()).get();
            float nav = (float) (Math.random() * (Math.random() * 1000));
            mutualFundStatistic.setNav(nav);
            mutualFundStatisticRepository.save(mutualFundStatistic);
            MutualFundPrice mutualFundPrice = new MutualFundPrice(null, nav, new Date(), mutualFundDetail);
            mutualFundPriceRepository.save(mutualFundPrice);
        });
    }
}

