package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockTradeHistoryRepository extends JpaRepository<StockTradeHistory, UUID> {

}
