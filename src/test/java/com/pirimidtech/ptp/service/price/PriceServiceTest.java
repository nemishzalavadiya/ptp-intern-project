package com.pirimidtech.ptp.service.price;

import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.repository.MutualFundPriceRepository;
import com.pirimidtech.ptp.repository.StockPriceRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
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
    private CompanyDetail companyDetail;

    @Before
    void setUp() {
        companyDetail=new CompanyDetail();
        companyDetail.setId(UUID.randomUUID());
        StockStatistic stockStatistic=new StockStatistic();
        stockStatistic.setId(UUID.fromString("4ee43b74-5582-49cb-a5c0-ee1bf638b995"));
        stockDetail=new StockDetail(UUID.fromString("4ee43b74-5582-49cb-a5c0-ee1bf638b995"),LocalDateTime.now(),"abc",companyDetail,stockStatistic);

    }

    @Test
    void addToStockPrice() {
        StockPrice stockPrice=new StockPrice(UUID.randomUUID(),100f, LocalDateTime.now(), StockExchangeType.BSE,stockDetail);
        priceService.addToStockPrice(stockPrice);
        verify(stockPriceRepository,times(1)).save(stockPrice);
    }

    @Test
    void getStockPrice() {
        UUID stockId=UUID.fromString("4ee43b74-5582-49cb-a5c0-ee1bf638b995");
        List<StockPrice> stockPriceList=new ArrayList<>();
        stockPriceList.add(new StockPrice(UUID.randomUUID(),100f, LocalDateTime.now(), StockExchangeType.BSE,stockDetail));
        stockPriceList.add(new StockPrice(UUID.randomUUID(),200f, LocalDateTime.now(), StockExchangeType.BSE,stockDetail));
        stockPriceList.add(new StockPrice(UUID.randomUUID(),300f, LocalDateTime.now(), StockExchangeType.BSE,stockDetail));
        when(stockPriceRepository.findAllByStockDetailId(stockId,PageRequest.of(0, 3))).thenReturn(new PageImpl<>(stockPriceList));
        assertEquals(3,priceService.getStockPrice(stockId,0,3).size());
    }

    @Test
    void addToMutualFundPrice() {
        MutualFundDetail mutualFundDetail=new MutualFundDetail();
        mutualFundDetail.setId(UUID.randomUUID());
        mutualFundDetail.setCompanyDetail(companyDetail);
        MutualFundPrice mutualFundPrice=new MutualFundPrice(UUID.randomUUID(),200,LocalDateTime.now(),mutualFundDetail);
        priceService.addToMutualFundPrice(mutualFundPrice);
        verify(mutualFundPriceRepository,times(1)).save(mutualFundPrice);
    }

    @Test
    void getMutualFundPrice() {
        List<MutualFundPrice> mutualFundPriceList=new ArrayList<>();
        MutualFundDetail mutualFundDetail=new MutualFundDetail();
        mutualFundDetail.setId(UUID.randomUUID());
        mutualFundDetail.setCompanyDetail(companyDetail);
        mutualFundPriceList.add(new MutualFundPrice(UUID.randomUUID(),200,LocalDateTime.now(),mutualFundDetail));
        mutualFundPriceList.add(new MutualFundPrice(UUID.randomUUID(),400,LocalDateTime.now(),mutualFundDetail));
        when(mutualFundPriceRepository.findAllByMutualFundDetailId(mutualFundDetail.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<>(mutualFundPriceList));
        assertEquals(2,priceService.getMutualFundPrice(mutualFundDetail.getId(),0,2).size());
    }
}