package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MutualFundDetailRepository extends JpaRepository<MutualFundDetail, UUID> {
    public MutualFundDetail findByCompanyDetailId(UUID companyId);
}
