package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistControllerTest {

    @InjectMocks
    WatchlistController watchlistController;
    @Mock
    WatchlistService watchlistService;
    @Mock
    WatchlistEntryService watchlistEntryService;
    private TestDataStore testDataStore;

    @Before
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    public void getAllWatchlistId() {
        //mocking
        when(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid1, testDataStore.pageable)).thenReturn(new PageImpl<>(testDataStore.watchlistList));
        when(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid2, testDataStore.pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        //Asserts
        ResponseEntity<Page<Watchlist>> pageResponseEntity = watchlistController.getAllWatchlistId(testDataStore.userUuid1, 0, 10);
        assertEquals(Objects.requireNonNull(pageResponseEntity.getBody()).getContent(), testDataStore.watchlistList);

        pageResponseEntity = watchlistController.getAllWatchlistId(testDataStore.userUuid2, 0, 10);
        assertEquals(Objects.requireNonNull(pageResponseEntity.getBody()).getContent(), new ArrayList<>());
    }

    @Test
    public void getAllWatchlistEntry() {
        //mocking
        when(watchlistEntryService.getAllWatchlistEntryByWatchlistId(testDataStore.watchlistList.get(0).getId(), testDataStore.pageable)).thenReturn(new PageImpl<>(testDataStore.watchlistEntryList));
        when(watchlistEntryService.getAllWatchlistEntryByWatchlistId(testDataStore.watchlistList.get(1).getId(), testDataStore.pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        //Asserts
        ResponseEntity<Page<WatchlistEntry>> pageResponseEntity = watchlistController.getAllWatchlistEntry(testDataStore.watchlistList.get(0).getId(), 0, 10);
        assertEquals(Objects.requireNonNull(pageResponseEntity.getBody()).getContent(), testDataStore.watchlistEntryList);

        pageResponseEntity = watchlistController.getAllWatchlistEntry(testDataStore.watchlistList.get(1).getId(), 0, 10);
        assertEquals(Objects.requireNonNull(pageResponseEntity.getBody()).getContent(), new ArrayList<>());
    }
}