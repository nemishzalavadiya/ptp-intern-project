package com.pirimidtech.ptp.service.mutualfund;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.repository.MutualFundDetailRepository;
import com.pirimidtech.ptp.repository.MutualFundStatisticRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutualFundServiceTest {
    @Mock
    MutualFundDetailRepository mutualFundDetailRepository;

    @Mock
    MutualFundStatisticRepository mutualFundStatisticRepository;

    @InjectMocks
    MutualFundService mutualFundService;

    public MutualFundDetail createMutualDetail(UUID id, String name) {
        return new MutualFundDetail(id, new Date(), name, new AssetDetail(UUID.randomUUID(), "ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
    }

    @Test
    public void testGetMutualFundDetail() {
        MutualFundDetail mutualFundDetail = createMutualDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "darshan");
        when(mutualFundDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(mutualFundDetail));
        assertEquals(mutualFundDetail, mutualFundService.getMutualFundDetailsById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }

    @Test
    public void testGetAllMutualFundDetail() {
        Pageable paging = PageRequest.of(0, 5);
        List<MutualFundDetail> mutualFundDetailList = new ArrayList<>();
        mutualFundDetailList.add(createMutualDetail(UUID.randomUUID(), "devesh"));
        mutualFundDetailList.add(createMutualDetail(UUID.randomUUID(), "darshan"));
        Page<MutualFundDetail> page = new PageImpl<>(mutualFundDetailList);
        when(mutualFundDetailRepository.findAll(paging)).thenReturn(page);
        assertEquals(mutualFundDetailList, mutualFundService.getAllMutualFundsDetails(paging).getContent());
    }

    @Test
    public void testGetMutualFundStats() {
        MutualFundStatistic mutualFundStatistic = new MutualFundStatistic(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "high", (float) 1.0, true, (float) 25.36, (float) 98.36, LocalDateTime.now(), (float) 45.2,
                createMutualDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "darshan"));
        when(mutualFundStatisticRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(mutualFundStatistic));
        assertEquals(mutualFundStatistic, mutualFundService.getMutualFundStatsById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }
}
