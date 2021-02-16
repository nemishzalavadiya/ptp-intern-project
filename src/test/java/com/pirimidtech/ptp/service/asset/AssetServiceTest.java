package com.pirimidtech.ptp.service.asset;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceTest {
    @Mock
    AssetDetailRepository assetDetailRepository;

    @InjectMocks
    AssetService assetService;

    public AssetDetail createAssetDetail() {
        return new AssetDetail(UUID.randomUUID(), "name", "logo_url", AssetClass.STOCK, "about", "devesh", "org");
    }

    public AssetDetail createAssetDetail(String name) {
        return new AssetDetail(UUID.randomUUID(), name, "logo_url", AssetClass.STOCK, "about", "devesh", "org");
    }

    public AssetDetail createAssetDetail(UUID id) {
        return new AssetDetail(id, "name", "logo_url", AssetClass.STOCK, "about", "devesh", "org");
    }


    @Test
    public void testGetAssetDetail() {
        AssetDetail assetDetail = createAssetDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"));
        when(assetDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(assetDetail));
        Optional<AssetDetail> assetDetail1 = assetService.getAssetDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"));
        assertEquals(assetDetail, assetDetail1.get());
    }

    @Test
    public void testGetAllAssetDetail() {
        Pageable paging = PageRequest.of(0, 5);
        List<AssetDetail> assetDetailList = new ArrayList<>();
        assetDetailList.add(createAssetDetail());
        assetDetailList.add(createAssetDetail());
        assetDetailList.add(createAssetDetail());
        Page<AssetDetail> page = new PageImpl<>(assetDetailList);
        when(assetDetailRepository.findAll(paging)).thenReturn(page);
        assertEquals(assetService.getAllAssetDetail(paging).getContent(), assetDetailList);
    }

    @Test
    public void testSearchAssetList() {
        Pageable paging = PageRequest.of(0, 5);
        List<AssetDetail> assetDetailList = new ArrayList<>();
        assetDetailList.add(createAssetDetail("ptp1"));
        assetDetailList.add(createAssetDetail("ptp2"));
        Page<AssetDetail> page = new PageImpl<>(assetDetailList);
        when(assetDetailRepository.findByNameContainingIgnoreCase("ptp", paging)).thenReturn(page);
        assertEquals(assetService.searchAssetDetail("ptp", paging).getContent(), assetDetailList);
    }
}
