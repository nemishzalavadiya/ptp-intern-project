package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.repository.WatchlistRepository;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import com.pirimidtech.ptp.util.TestDataStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceMockTest {

    @InjectMocks
    WatchlistService watchlistService;
    @Mock
    WatchlistRepository watchlistRepository;
    private TestDataStore testDataStore;

    @Before
    public void setUp() {
        testDataStore = new TestDataStore();
    }

    @Test
    public void getWatchlistDetailByUserId() {
        //When user do have watchlist
        when(watchlistRepository.findByUserId(testDataStore.userUuid1, testDataStore.pageable)).thenReturn(new PageImpl<>(testDataStore.watchlistList));
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid1, testDataStore.pageable).getContent(), testDataStore.watchlistList);

        //When user don't have watchlist
        when(watchlistRepository.findByUserId(testDataStore.userUuid2, testDataStore.pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertEquals(watchlistService.getWatchlistDetailByUserId(testDataStore.userUuid2, testDataStore.pageable).getContent(), new ArrayList<>());
    }

    @Test
    void add() {
        AssetDetail assetDetail = new AssetDetail();
        assetDetail.setId(UUID.randomUUID());
        Watchlist watchlist = new Watchlist(UUID.randomUUID(),
                new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"encryptedPassword", "abc", "abc@dev.com", "", "", "", "", Gender.MALE, ""),
                "", "");
        watchlistService.add(watchlist);
        verify(watchlistRepository, times(1)).save(watchlist);
    }
}
