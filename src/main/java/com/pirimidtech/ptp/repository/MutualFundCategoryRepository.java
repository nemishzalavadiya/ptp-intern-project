package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.MutualFundCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MutualFundCategoryRepository extends JpaRepository<MutualFundCategory, UUID> {
}
