package com.pirimidtech.ptp;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class MutualFundServiceMockTest {
    @Mock
    MutualFundDetailRepository mutualFundDetailRepository;

    @Mock
    MutualFundStatisticRepository mutualFundStatisticRepository;

    @InjectMocks
    MutualFundService mutualFundService;

    @Test
    public void testGetMutualFundDetail(){
        MutualFundDetail mutualFundDetail = new MutualFundDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), LocalDateTime.now(),"darshan", new AssetDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
        when(mutualFundDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(mutualFundDetail));
        assertEquals(mutualFundDetail, mutualFundService.getMutualFundDetailsById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }

    @Test
    public void testGetAllMutualFundDetail(){
        List<MutualFundDetail> mutualFundDetailList = new ArrayList<>();
        mutualFundDetailList.add(new MutualFundDetail(UUID.randomUUID(), LocalDateTime.now(),"devesh", new AssetDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org")));
        mutualFundDetailList.add(new MutualFundDetail(UUID.randomUUID(),LocalDateTime.now(),"darshan", new AssetDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "darshan", "org")));
        when(mutualFundDetailRepository.findAll()).thenReturn(mutualFundDetailList);
        assertEquals(mutualFundDetailList,mutualFundService.getAllMutualFundsDetails());
    }

    @Test
    public void testGetMutualFundStats(){
        MutualFundStatistic mutualFundStatistic = new MutualFundStatistic(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "high" , (float)1.0, true, (float)25.36, (float)98.36, LocalDateTime.now(), (float)45.2,
                new MutualFundDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), LocalDateTime.now(),"darshan", new AssetDetail(UUID.randomUUID(),"ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org")));
        when(mutualFundStatisticRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(mutualFundStatistic));
        assertEquals(mutualFundStatistic, mutualFundService.getMutualFundStatsById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }
}
