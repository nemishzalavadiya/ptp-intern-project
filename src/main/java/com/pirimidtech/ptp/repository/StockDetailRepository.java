package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.stockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StockDetailRepository extends JpaRepository<stockDetail, UUID> {
}
