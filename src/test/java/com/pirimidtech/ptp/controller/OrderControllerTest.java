package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtpApplication.class
)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    UUID stockOrderId = UUID.fromString("8eff12b1-c194-4849-9085-8ea6e7271527"), mutualFundOrderId = null;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void addToStockOrder() {
        StockTrade stockTrade = ObjectUtility.stockTrade1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(stockTrade);
            MvcResult mvcResult = mockMvc.perform(post("/stock/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk()).
                    andReturn();
            stockOrderId = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<UUID>() {});
            System.out.println(stockOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getAllStockOrder() {
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/users/" + ObjectUtility.user.getId() + "?page=0&size=4")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value(ObjectUtility.user.getId().toString())).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void getStockOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/" + stockOrderId)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andDo(print()).
                    andExpect(jsonPath("$.id").value(stockOrderId.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    void addToMutualFundOrder() {
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(mutualFundOrder);
            MvcResult mvcResult = mockMvc.perform(post("/mutualfund/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk()).
                    andReturn();
                    mutualFundOrderId = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<UUID>() {});
                    System.out.println(mutualFundOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void getAllMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/users/" + ObjectUtility.user.getId() + "?page=0&size=3")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value(ObjectUtility.user.getId().toString())).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    void getMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/" + mutualFundOrderId)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andDo(print()).
                    andExpect(jsonPath("$.id").value(mutualFundOrderId.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}