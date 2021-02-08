package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.MutualFundWatchlistDTO;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    @Before
    public void setUp(){
        userList = new ArrayList<>();
        companyDetailList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-00000000000"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth",Gender.MALE,"dpUrl"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000001"),"name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000010"),"name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000006"),userList.get(0),companyDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000007"),userList.get(0),companyDetailList.get(1)));
    }

    @Test
    public void displayWatchlist() throws Exception {
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Pageable pageable= PageRequest.of(0,10);
        when(watchListRepository.findByUserIdAndCompanyDetailAssetClass(userUuid,AssetClass.STOCK,pageable)).thenReturn(watchlistList.subList(0,1));
        when(watchListRepository.findByUserIdAndCompanyDetailAssetClass(userUuid,AssetClass.MUTUAL_FUND,pageable)).thenReturn(watchlistList.subList(1,2));
        when(companyDetailRepository.findById(companyDetailList.get(0).getId())).thenReturn(Optional.of(companyDetailList.get(0)));
        when(companyDetailRepository.findById(companyDetailList.get(1).getId())).thenReturn(Optional.of(companyDetailList.get(1)));

        // stocks
        MvcResult result = mockMvc.perform(get("/watchlist/stocks/00000000-0000-0000-0000-000000000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000001\"]");

        // mutual funds
        result = mockMvc.perform(get("/watchlist/mutualfunds/00000000-0000-0000-0000-000000000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000010\"]");
        //bad request
        mockMvc.perform(get("/watchlist/stocks/00000000-00000-000000000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/stocks/00000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/stocks?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
        mockMvc.perform(get("/watchlist/mutualfunds/00000000-00000-000000000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/mutualfunds/00000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/mutualfunds?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}
