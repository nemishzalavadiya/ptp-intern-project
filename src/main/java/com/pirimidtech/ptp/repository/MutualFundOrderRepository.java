package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MutualFundOrderRepository extends JpaRepository<MutualFundOrder, UUID> {

    Page<MutualFundOrder> findAllByUserIdOrderBySIPDateDesc(UUID userId, Pageable pageable);

    @Query(value = "SELECT * FROM MUTUAL_FUND_ORDER m WHERE EXTRACT (Day from m.sipdate)= ?1 and m.investment_type='MONTHLY_SIP' and m.sip_status='ACTIVE'", nativeQuery = true)
    List<MutualFundOrder> findAllByUserIdOrderByDay(int day);

    @Query(value = "SELECT * FROM MUTUAL_FUND_ORDER m WHERE EXTRACT (DOW FROM m.sipdate)= ?1 and m.investment_type='WEEKLY_SIP' and m.sip_status='ACTIVE'", nativeQuery = true)
    List<MutualFundOrder> findAllByUserIdOrderByWeekDay(int weekDay);

    @Query(value = "SELECT * FROM MUTUAL_FUND_ORDER m WHERE EXTRACT (DOY FROM m.sipdate)= ?1 and m.investment_type='YEARLY_SIP' and m.sip_status='ACTIVE'", nativeQuery = true)
    List<MutualFundOrder> findAllByUserIdOrderByYearDay(int yearDay);

    List<MutualFundOrder> findAllByStatus(Status status);

    Page<MutualFundOrder> findAllByUserIdAndTimestampBetween(UUID userId, Date startDate, Date endDate, Pageable pageable);

    Page<MutualFundOrder> findAllBySipStatusNotAndInvestmentTypeNotAndInvestmentTypeNotAndAndUserIdOrderBySIPDateDesc(SIPStatus deleted, InvestmentType oneTime,InvestmentType none, UUID userId, Pageable pageable);

}
