package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MutualFundOrderRepository extends JpaRepository<MutualFundOrder, UUID> {
    Page<MutualFundOrder> findAllByUserId(UUID userId, Pageable pageable);
}
