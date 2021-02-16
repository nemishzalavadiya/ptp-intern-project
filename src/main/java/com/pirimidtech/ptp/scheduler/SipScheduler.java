package com.pirimidtech.ptp.scheduler;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.repository.MutualFundOrderRepository;
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

    @Scheduled(cron = "0 0 10 * * *")
    public void trigger() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<MutualFundOrder> mutualFundOrderList = mutualFundOrderRepository.findAllByUserIdOrderByDay(day);
        mutualFundOrderList.forEach(mutualFundOrder -> {
                    Position position = new Position(null, 0, mutualFundOrder.getPrice(), AssetClass.MUTUAL_FUND, mutualFundOrder.getUser(), mutualFundOrder.getMutualFundDetail().getAssetDetail());
                    positionService.addToPosition(position, null);
                }
        );
    }
}
