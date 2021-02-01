package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.CompanyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyDetailRepository extends JpaRepository<CompanyDetail, UUID> {
    List<CompanyDetail> findByNameContainingAndAssetClass(String name, String assetClass);
    //CompanyDetail save(CompanyDetail companyDetail);
    public List<CompanyDetail> findByNameContaining(String infix);
}
