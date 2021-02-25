package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.utility.ObjectUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    void getStockPositionByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/position/assets/stock/users/" + ObjectUtility.user.getId() + "?page=0&size=1")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }
    @Test
    void getMutualFundPositionByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/position/assets/mutualFund/users/" + ObjectUtility.user.getId() + "?page=0&size=1")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }
    @Test
    void searchInStockPosition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/position/search/assets/stock/users/" + ObjectUtility.user.getId() + "?name=ptp&page=0&size=1")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }
    @Test
    void searchInMutualFundPosition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/position/search/assets/mutualFund/users/" + ObjectUtility.user.getId() + "?name=ptp&page=0&size=1")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andDo(print());
    }
}