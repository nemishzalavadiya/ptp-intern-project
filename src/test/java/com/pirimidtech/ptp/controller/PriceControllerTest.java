package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStockPrice() throws Exception {
        this.mockMvc.perform(get("/stock/" + ObjectUtility.stockPrice1.getId().toString() + "/prices?page=0&size=1")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getMutualFundPrice() throws Exception {
        this.mockMvc.perform(get("/mutualfund/" + ObjectUtility.mutualFundPrice1.getId().toString() + "/prices?page=0&size=1")).andExpect(status().is2xxSuccessful());
    }
}