package com.pirimidtech.ptp.service.asset;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AssetService implements AssetServiceInterface {
    @Autowired
    private AssetDetailRepository assetDetailRepository;
    @Override
    public List<AssetDetail> getAllAssetDetail(){
        return assetDetailRepository.findAll();
    }
    @Override
    public List<AssetDetail> searchAssetDetail(String infix){
        return assetDetailRepository.findByNameContaining(infix);
    }
    @Override
    public Optional<AssetDetail> getAssetDetail(UUID id) {
        return assetDetailRepository.findById(id);
    }
    @Override
    public void addCompany(AssetDetail assetDetail) {
        assetDetailRepository.save(assetDetail);
    }

    public AssetDetail searchByNameAndAsset(String name, AssetClass assetClass){
        return assetDetailRepository.findByNameContainingAndAssetClass(name,assetClass);
    }

}