package com.pirimidtech.ptp.service.tradeHistory;

import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.StockTradeHistoryRepository;
import com.pirimidtech.ptp.utility.ObjectUtility;
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

    private User user = ObjectUtility.user;

    @Test
    void getStockTradeHistory() {
        List<StockTradeHistory> stockTradeHistoryList = new ArrayList<>();
        stockTradeHistoryList.add(ObjectUtility.stockTradeHistory1);
        stockTradeHistoryList.add(ObjectUtility.stockTradeHistory2);
        when(stockTradeHistoryRepository.findAllByUserId(user.getId(), PageRequest.of(0, 2))).thenReturn(new PageImpl<>(stockTradeHistoryList));
        assertEquals(2, stockTradeHistoryService.getStockTradeHistory(user.getId(), 0, 2).size());
    }

    @Test
    void addToStockTradeHistory() {
        StockTradeHistory stockTradeHistory = ObjectUtility.stockTradeHistory1;
        stockTradeHistoryService.addToStockTradeHistory(stockTradeHistory);
        verify(stockTradeHistoryRepository, times(1)).save(stockTradeHistory);
    }

    @Test
    void getStockTradeByTradeId() {
        UUID tradeId = ObjectUtility.stockTradeHistory1.getId();
        StockTradeHistory stockTradeHistory = ObjectUtility.stockTradeHistory1;
        when(stockTradeHistoryRepository.findById(tradeId)).thenReturn(java.util.Optional.of(stockTradeHistory));
    }
}