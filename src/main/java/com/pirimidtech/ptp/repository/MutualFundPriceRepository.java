package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MutualFundPriceRepository extends JpaRepository<MutualFundPrice, UUID> {
    List<MutualFundPrice> findAllByMutualFundDetailIdAndTimestampBetween(UUID mutualFundId, Date date1, Date date2);
}
