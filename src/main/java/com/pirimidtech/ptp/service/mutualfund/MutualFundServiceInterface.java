package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MutualFundServiceInterface {
    List<MutualFundDetail> getAllMutualFundsDetails();

    Optional<MutualFundDetail> getMutualFundDetailsById(UUID id);

    Optional<MutualFundStatistic> getMutualFundStatsById(UUID id);
}
