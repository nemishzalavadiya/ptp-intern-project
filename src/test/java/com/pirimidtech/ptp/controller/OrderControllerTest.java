package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.OrderType;
import com.pirimidtech.ptp.entity.PriceType;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
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
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addToStockOrder() {
        StockOrder stockOrder = new StockOrder(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"), null, 100, Action.BUY, StockExchangeType.BSE, PriceType.LIMIT, OrderType.DELIVERY, 100f, "Active", new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"), "abc", "abc@dev.com", "", "", "", "", Gender.MALE, ""), null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(stockOrder);
            mockMvc.perform(post("/stock/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllStockOrder() {
        try {

            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/users/96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value("96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getStockOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.id").value("96312e56-e122-4fa4-b0ec-c8afa80d6779"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateStockOrder() {
        StockOrder stockOrder = new StockOrder();
        stockOrder.setOrderType(OrderType.INTRA_DAY);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            String requestJson = ow.writeValueAsString(stockOrder);
            mockMvc.perform(MockMvcRequestBuilders.put("/stock/orders/96312e56-e122-4fa4-b0ec-c8afa80d6779").
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
            mockMvc.perform(MockMvcRequestBuilders.delete("/stock/orders/d92437f9-2823-41b1-adb8-d9230f483bba")).
                    andExpect(status().isBadRequest());
            mockMvc.perform(MockMvcRequestBuilders.delete("/stock/orders/96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void addToMutualFundOrder() {
        MutualFundOrder mutualFundOrder = new MutualFundOrder(UUID.randomUUID(), null, 100f, InvestmentType.MONTHLY_SIP, null, new User(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"), "abc", "abc@dev.com", "", "", "", "", Gender.MALE, ""));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(mutualFundOrder);
            mockMvc.perform(post("/mutualfund/orders").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateMutualFundOrder() {
        User user = new User();
        user.setId(UUID.fromString("96312e56-e122-4fa4-b0ec-c8afa80d6779"));
        MutualFundOrder mutualFundOrder = new MutualFundOrder(null, null, 1000f, InvestmentType.MONTHLY_SIP, null, user);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try {
            String requestJson = ow.writeValueAsString(mutualFundOrder);
            mockMvc.perform(MockMvcRequestBuilders.put("/mutualfund/orders/d6a99154-11ac-4b5a-9402-59a9205a231d").
                    contentType(MediaType.APPLICATION_JSON).
                    content(requestJson)).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/users/96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.[0].user.id").value("96312e56-e122-4fa4-b0ec-c8afa80d6779")).
                    andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/d6a99154-11ac-4b5a-9402-59a9205a231d")).
                    andExpect(status().isOk()).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                    andExpect(jsonPath("$.id").value("d6a99154-11ac-4b5a-9402-59a9205a231d"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteMutualFundOrder() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/mutualfund/orders/d92437f9-2823-41b1-adb8-d9230f483bba")).
                    andExpect(status().isBadRequest());
            mockMvc.perform(MockMvcRequestBuilders.delete("/mutualfund/orders/d6a99154-11ac-4b5a-9402-59a9205a231d")).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}