package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockTradeRepository extends JpaRepository<StockTrade, UUID> {
    Page<StockTrade> findAllByUserIdOrderByTimestampDesc(UUID userId, Pageable pageable);

    List<StockTrade> findAllByStatus(Status status);
}
