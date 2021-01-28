package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MutualFundPriceRepository extends JpaRepository<MutualFundPrice, UUID> {
}
