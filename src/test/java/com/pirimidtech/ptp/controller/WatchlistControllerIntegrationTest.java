package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.repository.*;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WatchlistControllerIntegrationTest {

    private TestDataStore testDataStore;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WatchlistRepository watchlistRepository;

    @MockBean
    private WatchlistEntryRepository watchlistEntryRepository;

    @Before
    public void setUp(){
        testDataStore= new TestDataStore();
    }

    @Test
    public void displayWatchlist() throws Exception {

        //mocking
        when(watchlistRepository.findByUserId(testDataStore.userList.get(0).getId())).thenReturn(testDataStore.watchlistList);
        when(watchlistEntryRepository.findByWatchlistId(testDataStore.watchlistList.get(0).getId(),testDataStore.pageable)).thenReturn(testDataStore.watchlistEntryList);

        //get watchlist entries
        MvcResult result = mockMvc.perform(get("/watchlist/"+testDataStore.watchlistList.get(0).getId()+"?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000001\"]");

        //get watchlist ids
        result = mockMvc.perform(get("/watchlist/users/"+testDataStore.userList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),"[\"00000000-0000-0000-0000-000000000006\",\"00000000-0000-0000-0000-000000000007\"]");

        //bad request
        mockMvc.perform(get("/watchlist/00000000-00000-000000000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/00000000?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/users")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
