package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MutualFundOrderRepository extends JpaRepository<MutualFundOrder, UUID> {
    Page<MutualFundOrder> findAllByUserIdOrderBySIPDateDesc(UUID userId, Pageable pageable);

    @Query(value = "SELECT * FROM MUTUAL_FUND_ORDER m WHERE EXTRACT (Day from m.sipdate)= ?1 and m.investment_type='MONTHLY_SIP'", nativeQuery = true)
    List<MutualFundOrder> findAllByUserIdOrderByDay(int day);
}
