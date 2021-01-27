package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchListRepository extends JpaRepository<WatchList, UUID> {
}
