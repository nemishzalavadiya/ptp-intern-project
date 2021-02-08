package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockTradeHistoryRepository extends JpaRepository<StockTradeHistory, UUID> {
    Page<StockTradeHistory> findAllByUserId(UUID userId, Pageable pageable);
}
