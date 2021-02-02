package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockTradeHistoryServiceTest {

    @Autowired
    StockTradeHistoryService stockTradeHistoryService;

    @MockBean
    StockTradeHistoryRepository stockTradeHistoryRepository;

    User user=new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"abc","abc@dev.com","","","","", Gender.MALE,"");

    @Test
    void getStockTradeHistory() {
        List<StockTradeHistory> stockTradeHistoryList=new ArrayList<>();
        stockTradeHistoryList.add(new StockTradeHistory(UUID.randomUUID(),null,100, StockExchangeType.BSE,100f,user,null));
        stockTradeHistoryList.add(new StockTradeHistory(UUID.randomUUID(),null,200, StockExchangeType.BSE,100f,user,null));
        when(stockTradeHistoryRepository.findAllByUserId(user.getId())).thenReturn(stockTradeHistoryList);
        assertEquals(2,stockTradeHistoryService.getStockTradeHistory(user.getId()).size());
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