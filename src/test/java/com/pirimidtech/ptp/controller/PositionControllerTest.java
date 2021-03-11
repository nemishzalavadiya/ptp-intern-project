package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtpApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class PositionControllerTest {

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
    void getStockPositionByUser() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/position?page=0&size=1").cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }

    @Test
    void getMutualFundPositionByUser() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/position?page=0&size=1").cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }

    @Test
    void searchInStockPosition() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/stock/position/search?name=ptp&page=0&size=1").cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }

    @Test
    void searchInMutualFundPosition() throws Exception {
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/mutualfund/position/search?name=ptp&page=0&size=1").cookie(cookie)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }
}