package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.repository.StockStatisticRepository;
import com.pirimidtech.ptp.repository.WatchListRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WatchlistControllerIntegrationTest {

    public List<User> userList;
    public List<CompanyDetail> companyDetailList;
    public List<StockDetail> stockDetailList;
    public List<StockStatistic> stockStatisticList;
    public List<MutualFundDetail> mutualFundDetailList;
    public List<MutualFundStatistic> mutualFundStatisticList;
    public List<Watchlist> watchlistList;
    public List<StockWatchlistDTO> testStockWatchlistDTOList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WatchListRepository watchListRepository;

    @MockBean
    private CompanyDetailRepository companyDetailRepository;

    @MockBean
    private StockStatisticRepository stockStatisticRepository;
    @Before
    public void setUp(){
        userList = new ArrayList<>();
        companyDetailList = new ArrayList<>();
        stockDetailList = new ArrayList<>();
        stockStatisticList = new ArrayList<>();
        mutualFundDetailList = new ArrayList<>();
        mutualFundStatisticList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        testStockWatchlistDTOList = new ArrayList<>();
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-00000000000"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth", Gender.MALE,"dpUrl"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000001"),"name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        stockDetailList.add(new StockDetail(UUID.fromString("00000000-0000-0000-0000-00000000002"), LocalDateTime.now(),"nemish",companyDetailList.get(0)));
        stockStatisticList.add(new StockStatistic(UUID.fromString("00000000-0000-0000-0000-00000000003"), 1000,  10.2f, 2.03f, 3.65f, 20.16f, 56.02f, 45.99f, 78.69f, 100.98f,stockDetailList.get(0)));
        mutualFundDetailList.add(new MutualFundDetail(UUID.fromString("00000000-0000-0000-0000-00000000004"),LocalDateTime.now(),"nemish", companyDetailList.get(0)));
        mutualFundStatisticList.add(new MutualFundStatistic(UUID.fromString("00000000-0000-0000-0000-00000005"), "high" , 1.0f, true, 25.36f, 98.36f, LocalDateTime.now(), 45.2f,mutualFundDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000006"),userList.get(0),companyDetailList.get(0)));
        testStockWatchlistDTOList.add(new StockWatchlistDTO(companyDetailList.get(0).getName(),0.0f,0.0f,0.0f,0.0f));
    }

    @Test
    public void displayWatchlist() throws Exception {
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        when(watchListRepository.findByUserId(userUuid)).thenReturn(watchlistList);
        when(companyDetailRepository.findById(companyDetailList.get(0).getId())).thenReturn(Optional.of(companyDetailList.get(0)));
        when(stockStatisticRepository.findById(stockStatisticList.get(0).getId())).thenReturn(Optional.of(stockStatisticList.get(0)));

        mockMvc.perform(get("/watchlist/stocks/00000000-0000-0000-0000-000000000000")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]."+StockWatchlistDTO.class.getFields()[0].getName()).value("name"))
                .andExpect(jsonPath("$[0]."+StockWatchlistDTO.class.getFields()[1].getName()).value(0.0))
                .andExpect(jsonPath("$[0]."+StockWatchlistDTO.class.getFields()[2].getName()).value(0.0))
                .andExpect(jsonPath("$[0]."+StockWatchlistDTO.class.getFields()[3].getName()).value(0.0))
                .andExpect(jsonPath("$[0]."+StockWatchlistDTO.class.getFields()[4].getName()).value(0.0));
        //bad request
        mockMvc.perform(get("/watchlist/stocks/00000000-00000-000000000000")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/stocks/00000000")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/stocks/")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}
