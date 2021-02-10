package com.pirimidtech.ptp.controller;

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

    @Autowired
    private MockMvc mockMvc;
    UUID stockOrderId=null,mutualFundOrderId=null;

    @Test
    void addToStockOrder() {
        StockTrade stockTrade = ObjectUtility.stockTrade1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(stockTrade);
            MvcResult mvcResult =mockMvc.perform(post("/stock/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk()).
                    andReturn();
            stockOrderId=UUID.nameUUIDFromBytes(mvcResult.getResponse().getContentAsByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllStockOrder() {
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/users/"+ObjectUtility.user.getId()+"?page=0&size=3")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value(ObjectUtility.user.getId().toString())).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getStockOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/"+stockOrderId)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andDo(print()).
                    andExpect(jsonPath("$.id").value(stockOrderId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateStockOrder() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            String requestJson = ow.writeValueAsString(ObjectUtility.stockTrade1);
            mockMvc.perform(MockMvcRequestBuilders.put("/stock/orders/"+stockOrderId).
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteStockOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/stock/orders/"+stockOrderId)).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void addToMutualFundOrder() {
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(mutualFundOrder);
            MvcResult mvcResult =mockMvc.perform(post("/mutualfund/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk()).
                    andDo(print()).
                    andDo(mvcResult1 -> {
                        ObjectUtility.mutualFundOrder1.setId(UUID.nameUUIDFromBytes(mvcResult1.getResponse().getContentAsByteArray()));
                    }).
                    andReturn();
            mutualFundOrderId=UUID.nameUUIDFromBytes(mvcResult.getResponse().getContentAsByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/users/"+ObjectUtility.user.getId()+"?page=0&size=3")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value(ObjectUtility.user.getId().toString())).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/"+mutualFundOrderId)).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andDo(print()).
                    andExpect(jsonPath("$.id").value(mutualFundOrderId.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateMutualFundOrder() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(ObjectUtility.mutualFundOrder1);
            mockMvc.perform(MockMvcRequestBuilders.put("/mutualfund/orders/"+mutualFundOrderId).
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteMutualFundOrder() {
        try {
            System.out.println(ObjectUtility.mutualFundOrder1.getId());
            mockMvc.perform(MockMvcRequestBuilders.delete("/mutualfund/orders/"+mutualFundOrderId)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}