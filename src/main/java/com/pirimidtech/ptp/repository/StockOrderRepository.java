package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.UUID;

public interface StockOrderRepository extends JpaRepository<StockOrder, UUID> {
    Page<StockOrder> findAllByUserId(UUID userId, Pageable pageable);
}
