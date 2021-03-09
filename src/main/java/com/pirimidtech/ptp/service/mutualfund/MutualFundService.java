package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.DTO.SelectedMutualFundFilter;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.QMutualFundStatistic;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.querydsl.core.BooleanBuilder;
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
    public Page<MutualFundStatistic> getAllMutualFundsStatistics(Pageable paging) {
        return mutualFundStatisticRepository.findAll(paging);
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

    public Page<MutualFundStatistic> getMutualFundsFilterResults(SelectedMutualFundFilter selectedMutualFundFilter, Pageable paging) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QMutualFundStatistic qMutualFundStatistic = QMutualFundStatistic.mutualFundStatistic;
        if (selectedMutualFundFilter != null) {
            if (selectedMutualFundFilter.getRisk() != null) {
                BooleanBuilder bBuilder = new BooleanBuilder();
                selectedMutualFundFilter.getRisk().forEach(risk -> bBuilder.or(qMutualFundStatistic.risk.eq(risk)));
                booleanBuilder.and(bBuilder);
            }
            if (selectedMutualFundFilter.getSipAllowed() != null) {
                BooleanBuilder bBuilder = new BooleanBuilder();
                selectedMutualFundFilter.getSipAllowed().forEach(item -> {
                    bBuilder.or(qMutualFundStatistic.sipAllowed.eq(Boolean.parseBoolean(item)));
                });
                booleanBuilder.and(bBuilder);
            }
            if (selectedMutualFundFilter.getFundSizeRange().getMaximum() != null) {
                booleanBuilder.and(qMutualFundStatistic.fundSize.loe(selectedMutualFundFilter.getFundSizeRange().getMaximum()));
            }
            if (selectedMutualFundFilter.getFundSizeRange().getMinimum() != null) {
                booleanBuilder.and(qMutualFundStatistic.fundSize.goe(selectedMutualFundFilter.getFundSizeRange().getMinimum()));
            }
        }
        return mutualFundStatisticRepository.findAll(booleanBuilder, paging);
    }
}
