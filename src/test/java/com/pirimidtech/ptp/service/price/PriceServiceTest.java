package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.StockPriceRepository;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PriceServiceTest {

    @Autowired
    private PriceService priceService;

    @MockBean
    private StockPriceRepository stockPriceRepository;

    @MockBean
    private MutualFundPriceRepository mutualFundPriceRepository;
    private StockDetail stockDetail;
    private AssetDetail assetDetail;

    @Before
    void setUp() {
        assetDetail = ObjectUtility.assetDetail;
        StockStatistic stockStatistic = ObjectUtility.stockStatistic;
        stockDetail = ObjectUtility.stockDetail;
    }

    @Test
    void addToStockPrice() {
        StockPrice stockPrice = ObjectUtility.stockPrice1;
        priceService.addToStockPrice(stockPrice);
        verify(stockPriceRepository, times(1)).save(stockPrice);
    }

    @Test
    void getStockPrice() {
        UUID stockId = ObjectUtility.stockDetail.getId();
        List<StockPrice> stockPriceList = new ArrayList<>();
        stockPriceList.add(ObjectUtility.stockPrice1);
        stockPriceList.add(ObjectUtility.stockPrice2);
        when(stockPriceRepository.findAllByStockDetailIdOrderByTimestampDesc(stockId, PageRequest.of(0, 2))).thenReturn(new PageImpl<>(stockPriceList));
        assertEquals(2, priceService.getStockPrice(stockId, 0, 2).size());
    }

    @Test
    void addToMutualFundPrice() {
        MutualFundPrice mutualFundPrice = ObjectUtility.mutualFundPrice1;
        priceService.addToMutualFundPrice(mutualFundPrice);
        verify(mutualFundPriceRepository, times(1)).save(mutualFundPrice);
    }

    @Test
    void getMutualFundPrice() {
        List<MutualFundPrice> mutualFundPriceList = new ArrayList<>();
        MutualFundDetail mutualFundDetail = ObjectUtility.mutualFundDetail;
        mutualFundPriceList.add(ObjectUtility.mutualFundPrice1);
        mutualFundPriceList.add(ObjectUtility.mutualFundPrice2);
        when(mutualFundPriceRepository.findAllByMutualFundDetailIdOrderByTimestampDesc(mutualFundDetail.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<>(mutualFundPriceList));
        assertEquals(2, priceService.getMutualFundPrice(mutualFundDetail.getId(), 0, 2).size());
    }
}