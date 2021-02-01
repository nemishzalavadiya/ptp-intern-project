package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {
    List<Position> findAllByUserId(UUID userId);
}

