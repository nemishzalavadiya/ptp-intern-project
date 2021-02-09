package com.pirimidtech.ptp.controller;
//
//import com.pirimidtech.ptp.entity.*;
//import com.pirimidtech.ptp.service.watchlist.WatchlistService;
//import com.pirimidtech.ptp.util.TestDataStore;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@RunWith(MockitoJUnitRunner.class)
public class WatchlistControllerTest {
//
//    private TestDataStore testDataStore;
//
//    @InjectMocks
//    WatchlistController watchlistController;
//
//    @Mock
//    WatchlistService watchlistService;
//
//    @Before
//    public void setUp(){
//        testDataStore= new TestDataStore();
//    }
//    @Test
//    public void displayWatchlist() {
//        //data
//        List<UUID> actualUuidList = new ArrayList<>();
//        UUID userUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
//        UUID userUuid2 = UUID.fromString("00000000-0000-0000-0000-999999999999");
//        UUID company1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
//        UUID company2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000010");
//        Pageable pageable= PageRequest.of(0,10);
//
//        //mocking
//        when(watchlistService.getWatchlistDetailByUserId(userUuid1,AssetClass.STOCK,pageable)).thenReturn(testDataStore.watchlistList.subList(0,1));
//        when(watchlistService.getWatchlistDetailByUserId(userUuid1,AssetClass.MUTUAL_FUND,pageable)).thenReturn(testDataStore.watchlistList.subList(1,2));
//        when(watchlistService.getWatchlistDetailByUserId(userUuid2,AssetClass.STOCK,pageable)).thenReturn(new ArrayList<>());
//        when(watchlistService.getWatchlistDetailByUserId(userUuid2,AssetClass.MUTUAL_FUND,pageable)).thenReturn(new ArrayList<>());
//
//        //Asserts
//        //When user have stock watchlist
//        actualUuidList.add(company1Uuid);
//        List<UUID> expectedUuidList = watchlistController.displayWatchlist("stocks","00000000-0000-0000-0000-000000000000",0,10);
//        assertEquals(expectedUuidList,actualUuidList);
//
//        //When user have mutual fund watchlist
//        actualUuidList.clear();
//        actualUuidList.add(company2Uuid);
//        expectedUuidList = watchlistController.displayWatchlist("mutualfunds","00000000-0000-0000-0000-000000000000",0,10);
//        assertEquals(expectedUuidList,actualUuidList);
//
//        //When user don't have stock watchlist
//        expectedUuidList = watchlistController.displayWatchlist("stocks","00000000-0000-0000-0000-999999999999",0,10);
//        assertEquals(expectedUuidList,new ArrayList<>());
//
//        //When user don't have mutual fund watchlist
//        expectedUuidList = watchlistController.displayWatchlist("mutualfunds","00000000-0000-0000-0000-999999999999",0,10);
//        assertEquals(expectedUuidList,new ArrayList<>());
//    }
}