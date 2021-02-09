package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.repository.WatchlistRepository;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceMockTest {

    private TestDataStore testDataStore;

    @InjectMocks
    WatchlistService watchlistService;

    @Mock
    WatchlistRepository watchlistRepository;

    @Before
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    public void getWatchlistDetailByUserId() {
        //When user do have watchlist
        when(watchlistRepository.findByUserId(testDataStore.userUuid1)).thenReturn(testDataStore.watchlistList);
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid1), testDataStore.watchlistList);

        //When user don't have watchlist
        when(watchlistRepository.findByUserId(testDataStore.userUuid2)).thenReturn(new ArrayList<>());
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid2), new ArrayList<>());
    }
}
