package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
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
class StockTradeHistoryServiceTest {

    @Autowired
    private StockTradeHistoryService stockTradeHistoryService;

    @MockBean
    private StockTradeHistoryRepository stockTradeHistoryRepository;

    private User user=new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"abc","abc@dev.com","","","","", Gender.MALE,"");

    @Test
    void getStockTradeHistory() {
        List<StockTradeHistory> stockTradeHistoryList=new ArrayList<>();
        stockTradeHistoryList.add(new StockTradeHistory(UUID.randomUUID(),null,100, StockExchangeType.BSE,100f,user,null));
        stockTradeHistoryList.add(new StockTradeHistory(UUID.randomUUID(),null,200, StockExchangeType.BSE,100f,user,null));
        when(stockTradeHistoryRepository.findAllByUserId(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<>(stockTradeHistoryList));
        assertEquals(2,stockTradeHistoryService.getStockTradeHistory(user.getId(),0,2).size());
    }

    @Test
    void addToStockTradeHistory() {
        StockTradeHistory stockTradeHistory=new StockTradeHistory(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de84642"),null,100, StockExchangeType.BSE,100f,user,null);
        stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        verify(stockTradeHistoryRepository,times(1)).save(stockTradeHistory);
    }

    @Test
    void getStockTradeByTradeId() {
        UUID tradeId=UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de84642");
        StockTradeHistory stockTradeHistory=new StockTradeHistory(tradeId,null,100, StockExchangeType.BSE,100f,user,null);
        when(stockTradeHistoryRepository.findById(tradeId)).thenReturn(java.util.Optional.of(stockTradeHistory));
    }
}