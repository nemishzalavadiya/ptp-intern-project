package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AssetDetailRepository extends JpaRepository<AssetDetail, UUID> {

    AssetDetail findByNameContainingAndAssetClass(String name, String assetClass);

    List<AssetDetail> findByNameContaining(String infix);
}
