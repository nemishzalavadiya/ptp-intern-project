package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.DTO.MutualFundFilterRequest;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.QMutualFundStatistic;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.querydsl.core.BooleanBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MutualFundService implements MutualFundServiceInterface {
    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;
    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    public Page<MutualFundDetail> getAllMutualFundsDetails(Pageable paging) {
        return mutualFundDetailRepository.findAll(paging);
    }

    public Optional<MutualFundDetail> getMutualFundDetailsById(UUID id) {
        return mutualFundDetailRepository.findById(id);
    }

    public Optional<MutualFundStatistic> getMutualFundStatsById(UUID id) {
        return mutualFundStatisticRepository.findById(id);
    }

    public Optional<MutualFundStatistic> getMutualFundStatisticByAssetId(UUID assetId) {
        return mutualFundStatisticRepository.findByMutualFundDetail_AssetDetail_id(assetId);
    }

    public Page<MutualFundStatistic> getMutualFundsFilterResults(MutualFundFilterRequest mutualFundFilterRequest, Pageable paging) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QMutualFundStatistic qMutualFundStatistic = QMutualFundStatistic.mutualFundStatistic;
        if (mutualFundFilterRequest != null) {
            if (mutualFundFilterRequest.getRisk() != null) {
                BooleanBuilder bBuilder = new BooleanBuilder();
                mutualFundFilterRequest.getRisk().forEach(risk -> bBuilder.or(qMutualFundStatistic.risk.eq(risk)));
                booleanBuilder.and(bBuilder);
            }
            if (mutualFundFilterRequest.getSipAllowed() != null) {
                BooleanBuilder bBuilder = new BooleanBuilder();
                mutualFundFilterRequest.getSipAllowed().forEach(item -> {
                    bBuilder.or(qMutualFundStatistic.sipAllowed.eq(Boolean.parseBoolean(item)));
                });
                booleanBuilder.and(bBuilder);
            }
            if (mutualFundFilterRequest.getCloseSize() != null) {
                booleanBuilder.and(qMutualFundStatistic.fundSize.loe(mutualFundFilterRequest.getCloseSize()));
            }
            if (mutualFundFilterRequest.getOpenSize() != null) {
                booleanBuilder.and(qMutualFundStatistic.fundSize.goe(mutualFundFilterRequest.getOpenSize()));
            }
        }
        return mutualFundStatisticRepository.findAll(booleanBuilder, paging);
    }
}
