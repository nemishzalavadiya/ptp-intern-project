package com.pirimidtech.ptp.service.asset;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AssetService implements AssetServiceInterface {
    @Autowired
    private AssetDetailRepository assetDetailRepository;

    @Override
    public Page<AssetDetail> getAllAssetDetail(Pageable paging) {
        return assetDetailRepository.findAll(paging);
    }

    @Override
    public Page<AssetDetail> searchAssetDetail(String infix, Pageable paging) {
        return assetDetailRepository.findByNameContainingIgnoreCase(infix, paging);
    }

    @Override
    public Optional<AssetDetail> getAssetDetail(UUID id) {
        return assetDetailRepository.findById(id);
    }

    @Override
    public void addCompany(AssetDetail assetDetail) {
        assetDetailRepository.save(assetDetail);
    }

    public List<AssetDetail> searchByNameAndAsset(String name, AssetClass assetClass) {
        return assetDetailRepository.findByNameContainingAndAssetClass(name, assetClass);
    }


    @Override
    public void addAsset(AssetDetail assetDetail) {
        assetDetailRepository.save(assetDetail);
    }

}