package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.Position;

import java.util.List;
import java.util.UUID;

public interface PositionServiceInterface {

    List<Position> getAllPosition(UUID userId,int pageNo,int pageSize);
    void deleteFromPosition(UUID positionId);
    Position addToPosition(Position position, Action action);
}
