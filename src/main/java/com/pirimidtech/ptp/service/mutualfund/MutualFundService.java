package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MutualFundService implements MutualFundServiceInterface {
    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;
    @Autowired
    private MutualFundStatisticRepository mutualFundStatisticRepository;

    public List<MutualFundDetail> getAllMutualFundsDetails(){
        return mutualFundDetailRepository.findAll();
    }
    public Optional<MutualFundDetail> getMutualFundDetailsById(UUID id){
        return mutualFundDetailRepository.findById(id);
    }
    public Optional<MutualFundStatistic> getMutualFundStatsById(UUID id){
        return mutualFundStatisticRepository.findById(id);
    }
    public Optional<MutualFundDetail> getMutualFundDetailByCompanyId(UUID id) {
        return mutualFundDetailRepository.findByCompanyDetailId(id);
    }
}
