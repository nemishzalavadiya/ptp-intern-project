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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    void getPosition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/position/users/" + ObjectUtility.user.getId() + "?page=0&size=1")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.[0].user.id").value(ObjectUtility.user.getId().toString())).
                andExpect(jsonPath("$.[0]").exists()).
                andDo(print());
    }
}