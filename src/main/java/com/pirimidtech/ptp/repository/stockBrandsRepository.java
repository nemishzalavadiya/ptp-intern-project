package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.stockBrands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface stockBrandsRepository extends JpaRepository<stockBrands, UUID> {

}
