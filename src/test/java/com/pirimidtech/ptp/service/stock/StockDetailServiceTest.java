package com.pirimidtech.ptp.service.stock;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.repository.StockDetailRepository;
import com.pirimidtech.ptp.repository.StockStatisticRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class StockDetailServiceTest {
    @Mock
    StockDetailRepository stockDetailRepository;

    @Mock
    StockStatisticRepository stockStatisticRepository;

    @InjectMocks
    StockService stockService;

    public StockDetail createStock(UUID id, String name) {
        return new StockDetail(id, LocalDateTime.now(), name, new AssetDetail(UUID.randomUUID(), "ptp1", "logo_url", AssetClass.STOCK, "about", "devesh", "org"));
    }

    @Test
    public void testGetStockDetail() {
        StockDetail stockDetail = createStock(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "darshan");
        when(stockDetailRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(stockDetail));
        assertEquals(stockDetail, stockService.getStockDetailById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }

    @Test
    public void testGetAllStockDetail() {
        Pageable paging = PageRequest.of(0, 5);
        List<StockDetail> stockDetailList = new ArrayList<>();
        stockDetailList.add(createStock(UUID.randomUUID(), "devesh"));
        stockDetailList.add(createStock(UUID.randomUUID(), "darshan"));
        Page<StockDetail> page = new PageImpl<>(stockDetailList);
        when(stockDetailRepository.findAll(paging)).thenReturn(page);
        assertEquals(stockDetailList, stockService.getAllStockDetails(paging).getContent());
    }

    @Test
    public void testGetStockStats() {
        StockStatistic stockStatistic = new StockStatistic(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), 1000, (float) 10.2, (float) 2.03, (float) 3.65, (float) 20.16, (float) 56.02, (float) 45.99, (float) 78.69, (float) 100.98,
                createStock(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"), "darshan"));
        when(stockStatisticRepository.findById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c"))).thenReturn(Optional.of(stockStatistic));
        assertEquals(stockStatistic, stockService.getStockStatsById(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b18c")).get());
    }
}
