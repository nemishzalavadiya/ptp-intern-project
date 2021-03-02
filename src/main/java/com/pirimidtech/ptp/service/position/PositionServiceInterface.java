package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.Position;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PositionServiceInterface {
    void deleteFromPosition(UUID positionId);

    void addStockToPosition(Position position, Action action);

    void addMutualFundToPosition(Position position);

    Page<Position> getPositionByAssetClass(UUID userId, AssetClass assetClass, int pageNo, int pageSize);
}
