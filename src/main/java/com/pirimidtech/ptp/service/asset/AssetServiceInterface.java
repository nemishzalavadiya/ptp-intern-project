package com.pirimidtech.ptp.service.asset;

import com.pirimidtech.ptp.entity.AssetDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AssetServiceInterface {
    Optional<AssetDetail> getAssetDetail(UUID id);

    void addAsset(AssetDetail assetDetail);

    Page<AssetDetail> getAllAssetDetail(Pageable paging);

    Page<AssetDetail> searchAssetDetail(String infix, Pageable paging);
}