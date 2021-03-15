package com.pirimidtech.ptp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.StockTrade;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import com.pirimidtech.ptp.util.TestDataStore;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.BeforeEach;
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

import javax.servlet.http.Cookie;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private TestDataStore testDataStore;

    @BeforeEach
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    @Order(1)
    void addToStockOrder() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        StockTrade stockTrade = ObjectUtility.stockTrade1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(stockTrade);
        MvcResult mvcResult = mockMvc.perform(post("/stock/orders").cookie(cookie).
                contentType(MediaType.APPLICATION_JSON).
                content(requestJson)).
                andExpect(status().isOk()).
                andDo(print()).
                andReturn();
        mapper.registerModule(new JavaTimeModule());
        StockTrade stockTrade1 = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<StockTrade>() {
        });
        stockOrderId = stockTrade1.getId();

    }

    @Test
    @Order(2)
    void getAllStockOrderByUser() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders?page=0&size=4").cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.content[0].user.id").value(ObjectUtility.user.getId().toString())).
                andDo(print());
    }

    @Test
    @Order(3)
    void getStockOrder() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/orders/" + stockOrderId).cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(jsonPath("$.id").value(stockOrderId.toString()));
    }

    @Test
    @Order(5)
    void addToMutualFundOrder() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        MutualFundOrder mutualFundOrder = ObjectUtility.mutualFundOrder1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(mutualFundOrder);
        MvcResult mvcResult = mockMvc.perform(post("/mutualfund/orders").cookie(cookie).
                contentType(MediaType.APPLICATION_JSON).
                content(requestJson)).
                andExpect(status().isOk()).
                andReturn();
        mapper.registerModule(new JavaTimeModule());
        MutualFundOrder mutualFundOrder1 = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<MutualFundOrder>() {
        });
        mutualFundOrderId = mutualFundOrder1.getId();
    }

    @Test
    @Order(7)
    void getMutualFundOrder() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/orders/" + mutualFundOrderId).cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(jsonPath("$.id").value(mutualFundOrderId.toString()));
    }
}