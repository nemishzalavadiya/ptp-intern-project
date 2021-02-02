package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Position;

import java.util.List;
import java.util.UUID;

public interface PositionServiceInterface {

    List<Position> getAllPosition(UUID userId);
    void deleteFromPosition(UUID positionId);
    void addToPosition(Position position);
}
