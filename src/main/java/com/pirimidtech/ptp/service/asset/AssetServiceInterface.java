package com.pirimidtech.ptp.service.asset;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface AssetServiceInterface {
    Optional<AssetDetail> getAssetDetail(UUID id);

    void addCompany(AssetDetail assetDetail);

    List<AssetDetail> getAllAssetDetail();

    List<AssetDetail> searchAssetDetail(String infix);
    public AssetDetail searchByNameAndAsset(String toLowerCase, AssetClass assetClass) ;

}