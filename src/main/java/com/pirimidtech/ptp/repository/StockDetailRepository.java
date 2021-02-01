package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StockDetailRepository extends JpaRepository<StockDetail, UUID> {
    public StockDetail findByCompanyDetailId(UUID companyId);
}
