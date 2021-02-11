package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
    List<StockOrder> findAllByUserId(UUID userId);
}
