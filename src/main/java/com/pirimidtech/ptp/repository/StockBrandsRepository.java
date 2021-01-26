package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.StockBrands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockBrandsRepository extends JpaRepository<StockBrands, UUID> {

}
