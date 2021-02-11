package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {
    Page<Position> findAllByUserId(UUID userId, Pageable pageable);

    Optional<Position> findAllByUserIdAndAssetDetailId(UUID userId, UUID assetDetailId);
}
