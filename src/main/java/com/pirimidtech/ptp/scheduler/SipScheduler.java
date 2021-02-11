package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SipScheduler {

    @Autowired
    PositionService positionService;

    @Autowired
    private MutualFundOrderRepository mutualFundOrderRepository;

    @Scheduled(cron = "0 0 10 * * *")
    public void trigger()
    {
        mutualFundOrderRepository.findAllBySIPDateAndAndInvestmentType(LocalDate.now(), InvestmentType.MONTHLY_SIP).stream().forEach(
                mutualFundOrder -> {
                    positionService.addToPosition(new Position(null, 0, mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(),mutualFundOrder.getMutualFundDetail().getAssetDetail()), null);
                }
        );
    }
}
