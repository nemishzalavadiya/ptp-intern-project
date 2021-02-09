package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.AssetDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetDetailRepository extends JpaRepository<AssetDetail, UUID> {
    public Page<AssetDetail> findByNameContainingIgnoreCase(String infix, Pageable paging);
}
