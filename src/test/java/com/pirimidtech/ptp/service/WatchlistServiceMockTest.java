package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.entity.Watchlist;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
        when(watchlistRepository.findByUserId(testDataStore.userUuid1,testDataStore.pageable)).thenReturn((Page<Watchlist>) testDataStore.watchlistList);
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid1,testDataStore.pageable).getContent(), testDataStore.watchlistList);

        //When user don't have watchlist
        when(watchlistRepository.findByUserId(testDataStore.userUuid2,testDataStore.pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid2,testDataStore.pageable).getContent(), new ArrayList<>());
    }
}
