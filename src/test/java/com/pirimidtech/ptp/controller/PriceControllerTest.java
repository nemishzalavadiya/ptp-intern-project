package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.StockPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addToStockPrice() throws Exception {
        StockPrice stockPrice = new StockPrice();
        stockPrice.setId(UUID.randomUUID());
        this.mockMvc.perform(post("/stock/prices").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(stockPrice))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getStockPrice() throws Exception {
        StockPrice stockPrice = new StockPrice();
        stockPrice.setId(UUID.randomUUID());
        this.mockMvc.perform(post("/stock/prices").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(stockPrice)));
        this.mockMvc.perform(get("/stock/"+stockPrice.getId().toString()+"/prices")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void addToMutualFundPrice() throws Exception {
        MutualFundPrice mutualFundPrice = new MutualFundPrice();
        mutualFundPrice.setId(UUID.randomUUID());
        this.mockMvc.perform(post("/mutualfund/prices").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(mutualFundPrice))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getMutualFundPrice() throws Exception {
        MutualFundPrice mutualFundPrice = new MutualFundPrice();
        mutualFundPrice.setId(UUID.randomUUID());
        this.mockMvc.perform(post("/mutualfund/prices").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(mutualFundPrice))).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/mutualfund/"+mutualFundPrice.getId().toString()+"/prices")).andExpect(status().is2xxSuccessful());
    }
}