package com.pirimidtech.ptp;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.repository.AssetDetailRepository;
import com.pirimidtech.ptp.service.asset.AssetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class AssetDetailServiceMockTest {
    @Mock
    AssetDetailRepository assetDetailRepository;

    @InjectMocks
    AssetService assetService;

    @Test
    public void testGetAssetDetail(){
        AssetDetail assetDetail = new AssetDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "name", "logo_url", AssetClass.STOCK, "about", "devesh", "org");
        when(assetDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(assetDetail));
        Optional<AssetDetail> assetDetail1 = assetService.getAssetDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"));
        assertEquals(assetDetail,assetDetail1.get());
    }

    @Test
    public void testGetAllAssetDetail(){
        List<AssetDetail> assetDetailList = new ArrayList<>();
        assetDetailList.add(new AssetDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        assetDetailList.add(new AssetDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        assetDetailList.add(new AssetDetail(UUID.randomUUID(),"name", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        when(assetDetailRepository.findAll()).thenReturn(assetDetailList);
        assertEquals(assetDetailList, assetService.getAllAssetDetail());
    }

    @Test
    public void testSearchCompanyList(){
        List<AssetDetail> assetDetailList = new ArrayList<>();
        assetDetailList.add(new AssetDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        assetDetailList.add(new AssetDetail(UUID.randomUUID(),"ptp2", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        when(assetDetailRepository.findByNameContaining("ptp")).thenReturn(assetDetailList);
        assertEquals(assetService.searchAssetDetail("ptp"), assetDetailList);
    }
}
