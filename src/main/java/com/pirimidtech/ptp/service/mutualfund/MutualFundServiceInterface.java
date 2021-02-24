package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MutualFundServiceInterface {
    Page<MutualFundDetail> getAllMutualFundsDetails(Pageable paging);

    Optional<MutualFundDetail> getMutualFundDetailsById(UUID id);

    Optional<MutualFundStatistic> getMutualFundStatsById(UUID id);

    Optional<MutualFundStatistic> getMutualFundStatisticByAssetId(UUID id);

    Page<MutualFundStatistic> getMutualFundsFilterByRisk(Pageable paging, String risk);

    Page<MutualFundStatistic> getMutualFundsFilterBySip(Pageable paging, boolean sipAllowed);

    Page<MutualFundStatistic> getMutualFundsFilterByFundSize(Pageable paging, float sizeOpen,float sizeClose);
}
