package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.stockStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface stockStatisticRepository extends JpaRepository<stockStatistic, UUID> {

}
