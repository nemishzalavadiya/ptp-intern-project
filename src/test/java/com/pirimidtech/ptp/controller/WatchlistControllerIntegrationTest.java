package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.repository.WatchlistEntryRepository;
import com.pirimidtech.ptp.repository.WatchlistRepository;
import com.pirimidtech.ptp.util.JwtTokenUtil;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private WatchlistRepository watchlistRepository;

    @MockBean
    private WatchlistEntryRepository watchlistEntryRepository;

    @Before
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    public void displayWatchlist() throws Exception {

        //mocking
        Cookie cookie = new Cookie("token", jwtTokenUtil.generateToken(testDataStore.userList.get(0)));
        when(watchlistRepository.findByUserId(testDataStore.userList.get(0).getId(), testDataStore.pageable)).thenReturn(new PageImpl<>(testDataStore.watchlistList));
        when(watchlistEntryRepository.findByWatchlistId(testDataStore.watchlistList.get(0).getId(), testDataStore.pageable)).thenReturn(new PageImpl<>(testDataStore.watchlistEntryList));

        //get watchlist entries
        MvcResult result = mockMvc.perform(get("/watchlist/" + testDataStore.watchlistList.get(0).getId() + "?page=0&size=10").cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String actual = "{\"content\":[{\"id\":\"00000000-0000-0000-0000-000000000008\",\"watchlist\":{\"id\":\"00000000-0000-0000-0000-000000000006\",\"user\":{\"id\":\"00000000-0000-0000-0000-000000000000\",\"password\":\"encryptedPassword\",\"name\":\"Nemish\",\"email\":\"email\",\"panCard\":\"panCard\",\"mobileNo\":\"mobileNo\",\"signatureUrl\":\"signature\",\"dateOfBirth\":\"dataOfBirth\",\"gender\":\"MALE\",\"dpURL\":\"dpUrl\"},\"name\":\"myFirst\",\"description\":\"description\"},\"assetDetail\":{\"id\":\"00000000-0000-0000-0000-000000000001\",\"name\":\"name\",\"logoUrl\":\"logo_url\",\"assetClass\":\"STOCK\",\"about\":\"about\",\"managingDirector\":\"nemish\",\"organization\":\"org\"}}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":1,\"totalPages\":1,\"size\":1,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), actual, false);

        //get watchlist ids
        result = mockMvc.perform(get("/watchlist/user").cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        actual = "{\"content\":[{\"id\":\"00000000-0000-0000-0000-000000000006\",\"user\":{\"id\":\"00000000-0000-0000-0000-000000000000\",\"password\":\"encryptedPassword\",\"name\":\"Nemish\",\"email\":\"email\",\"panCard\":\"panCard\",\"mobileNo\":\"mobileNo\",\"signatureUrl\":\"signature\",\"dateOfBirth\":\"dataOfBirth\",\"gender\":\"MALE\",\"dpURL\":\"dpUrl\"},\"name\":\"myFirst\",\"description\":\"description\"},{\"id\":\"00000000-0000-0000-0000-000000000007\",\"user\":{\"id\":\"00000000-0000-0000-0000-000000000000\",\"password\":\"encryptedPassword\",\"name\":\"Nemish\",\"email\":\"email\",\"panCard\":\"panCard\",\"mobileNo\":\"mobileNo\",\"signatureUrl\":\"signature\",\"dateOfBirth\":\"dataOfBirth\",\"gender\":\"MALE\",\"dpURL\":\"dpUrl\"},\"name\":\"myFirst\",\"description\":\"description\"}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":2,\"last\":true,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":2,\"first\":true,\"empty\":false}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), actual, actual, false);

        //bad request
        mockMvc.perform(get("/watchlist/00000000-00000-000000000000?page=0&size=10").cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/00000000?page=0&size=10").cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/users").cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
        mockMvc.perform(get("/watchlist/users")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }
}
