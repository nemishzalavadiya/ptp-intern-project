package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.service.watchlist.WatchlistEntryService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistControllerTest {

    private TestDataStore testDataStore;

    @InjectMocks
    WatchlistController watchlistController;

    @Mock
    WatchlistService watchlistService;

    @Mock
    WatchlistEntryService watchlistEntryService;

    @Before
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    public void getAllWatchlistId() {
        //mocking
        when(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid1)).thenReturn(testDataStore.watchlistList);
        when(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid2)).thenReturn(new ArrayList<>());

        //Asserts
        List<UUID> expectedUuidList = watchlistController.getAllWatchlistId(testDataStore.userUuid1);
        assertEquals(expectedUuidList, testDataStore.watchlistList.stream().map(Watchlist::getId).collect(Collectors.toList()));

        expectedUuidList = watchlistController.getAllWatchlistId(testDataStore.userUuid2);
        assertEquals(expectedUuidList, new ArrayList<>());
    }

    @Test
    public void getAllWatchlistEntry() {
        //mocking
        when(watchlistEntryService.getAllWatchlistEntryByWatchlistId(testDataStore.watchlistList.get(0).getId(), testDataStore.pageable)).thenReturn(testDataStore.watchlistEntryList);
        when(watchlistEntryService.getAllWatchlistEntryByWatchlistId(testDataStore.watchlistList.get(1).getId(), testDataStore.pageable)).thenReturn(new ArrayList<>());

        //Asserts
        List<UUID> expectedUuidList = watchlistController.getAllWatchlistEntry(testDataStore.watchlistList.get(0).getId(), 0, 10);
        assertEquals(expectedUuidList, Collections.singletonList(testDataStore.watchlistEntryList.get(0).getAssetDetail().getId()));

        expectedUuidList = watchlistController.getAllWatchlistEntry(testDataStore.watchlistList.get(1).getId(), 0, 10);
        assertEquals(expectedUuidList, new ArrayList<>());
    }
}