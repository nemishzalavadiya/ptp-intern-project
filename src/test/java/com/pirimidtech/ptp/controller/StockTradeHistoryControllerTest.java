package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockTradeHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addToStockTradeHistory() throws Exception {
        StockTradeHistory stockTradeHistory = ObjectUtility.stockTradeHistory1;
        stockTradeHistory.setId(UUID.fromString("db3d9fab-3b1f-4fcd-b038-82a335275942"));
        User user = ObjectUtility.user;
        user.setId(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"));
        stockTradeHistory.setUser(user);
        this.mockMvc.perform(post("/stock/trade-history").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(stockTradeHistory))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getStockTradeHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/trade-history/user/96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.[0].user.id").value("96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                andExpect(jsonPath("$.[0]").exists()).
                andDo(print());
    }

    @Test
    void getStockTradeByTradeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/trade-history/db3d9fab-3b1f-4fcd-b038-82a335275942")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.user.id").value("96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                andExpect(jsonPath("$").exists()).
                andDo(print());

    }
}