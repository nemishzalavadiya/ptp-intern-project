package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistControllerTest {
    public List<User> userList;
    public List<CompanyDetail> companyDetailList;
    public List<Watchlist> watchlistList;
    @InjectMocks
    WatchlistController watchlistController;
    @Mock
    WatchlistService watchlistService;
    @Before
    public void setUp(){
        userList = new ArrayList<>();
        companyDetailList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-000000000000"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth",Gender.MALE,"dpUrl"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-000000000001"),"name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-000000000010"),"name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000006"),userList.get(0),companyDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000007"),userList.get(0),companyDetailList.get(1)));
    }
    @Test
    public void displayStockWatchlist() {
        List<UUID> actualUuidList = new ArrayList<>();
        UUID company1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID company2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000010");

        //When user have stock watchlist
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Pageable pageable= PageRequest.of(0,10);
        actualUuidList.add(company1Uuid);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.STOCK,pageable)).thenReturn(watchlistList.subList(0,1));
        List<UUID> controllerStockWatchlistDTOList = watchlistController.displayWatchlist("stocks","00000000-0000-0000-0000-000000000000",0,10);
        assertEquals(controllerStockWatchlistDTOList,actualUuidList);

        //When user don't have stock watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-999999999999");
        pageable= PageRequest.of(0,10);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.STOCK,pageable)).thenReturn(new ArrayList<>());
        controllerStockWatchlistDTOList = watchlistController.displayWatchlist("stocks","00000000-0000-0000-0000-999999999999",0,10);
        assertEquals(controllerStockWatchlistDTOList,new ArrayList<>());

        //When user have mutual fund watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        actualUuidList.clear();
        actualUuidList.add(company2Uuid);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.MUTUAL_FUND,pageable)).thenReturn(watchlistList.subList(1,2));
        List<UUID> controllerMutualFundWatchlistDTOList = watchlistController.displayWatchlist("mutualfunds","00000000-0000-0000-0000-000000000000",0,10);
        assertEquals(controllerMutualFundWatchlistDTOList,actualUuidList);

        //When user don't have mutual fund watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-999999999999");
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.MUTUAL_FUND,pageable)).thenReturn(new ArrayList<>());
        controllerMutualFundWatchlistDTOList = watchlistController.displayWatchlist("mutualfunds","00000000-0000-0000-0000-999999999999",0,10);
        assertEquals(controllerMutualFundWatchlistDTOList,new ArrayList<>());
    }
}