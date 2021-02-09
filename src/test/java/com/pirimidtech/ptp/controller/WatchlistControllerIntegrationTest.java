package com.pirimidtech.ptp.controller;
//
//import com.pirimidtech.ptp.entity.*;
//import com.pirimidtech.ptp.repository.*;
//import com.pirimidtech.ptp.util.TestDataStore;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class WatchlistControllerIntegrationTest {
//
//    private TestDataStore testDataStore;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private WatchListRepository watchListRepository;
//
//    @MockBean
//    private CompanyDetailRepository companyDetailRepository;
//
//    @Before
//    public void setUp(){
//        testDataStore= new TestDataStore();
//    }
//
//    @Test
//    public void displayWatchlist() throws Exception {
//
//        //mocking
//        when(watchListRepository.findByUserIdAndCompanyDetailAssetClass(testDataStore.userList.get(0).getId(),AssetClass.STOCK,testDataStore.pageable)).thenReturn(testDataStore.watchlistList.subList(0,1));
//        when(watchListRepository.findByUserIdAndCompanyDetailAssetClass(testDataStore.userList.get(0).getId(),AssetClass.MUTUAL_FUND,testDataStore.pageable)).thenReturn(testDataStore.watchlistList.subList(1,2));
//        when(companyDetailRepository.findById(testDataStore.companyDetailList.get(0).getId())).thenReturn(Optional.of(testDataStore.companyDetailList.get(0)));
//        when(companyDetailRepository.findById(testDataStore.companyDetailList.get(1).getId())).thenReturn(Optional.of(testDataStore.companyDetailList.get(1)));
//
//        // stocks
//        MvcResult result = mockMvc.perform(get("/watchlist/stocks/00000000-0000-0000-0000-000000000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000001\"]");
//
//        // mutual funds
//        result = mockMvc.perform(get("/watchlist/mutualfunds/00000000-0000-0000-0000-000000000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000010\"]");
//
//        //bad request
//        mockMvc.perform(get("/watchlist/stocks/00000000-00000-000000000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
//        mockMvc.perform(get("/watchlist/stocks/00000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
//        mockMvc.perform(get("/watchlist/stocks?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
//        mockMvc.perform(get("/watchlist/mutualfunds/00000000-00000-000000000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
//        mockMvc.perform(get("/watchlist/mutualfunds/00000000?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
//        mockMvc.perform(get("/watchlist/mutualfunds?page=0&size=10")
//                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
//    }
}
