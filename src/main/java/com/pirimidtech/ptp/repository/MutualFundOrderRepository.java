package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MutualFundOrderRepository extends JpaRepository<MutualFundOrder, UUID> {
}
