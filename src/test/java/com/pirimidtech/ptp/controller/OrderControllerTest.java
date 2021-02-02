package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.PtpApplication;
import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.repository.StockOrderRepository;
import com.pirimidtech.ptp.repository.UserRepository;
import com.pirimidtech.ptp.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtpApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addToStockOrder() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/orders/stock"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllStockOrder() {
    }

    @Test
    void getStockOrder() {
    }

    @Test
    void updateStockOrder() {
    }

    @Test
    void deleteStockOrder() {
    }
}