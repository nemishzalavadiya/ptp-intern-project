package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface StockDetailRepository extends JpaRepository<StockDetail, UUID> {
    public Optional<StockDetail> findByCompanyDetailId(UUID companyId);
}
