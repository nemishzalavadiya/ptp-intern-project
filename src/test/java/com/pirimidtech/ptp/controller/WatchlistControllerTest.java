package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.exception.ExceptionHandler;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistControllerTest {

    public List<User> userList;
    public List<CompanyDetail> companyDetailList;
    public List<StockDetail> stockDetailList;
    public List<StockStatistic> stockStatisticList;
    public List<MutualFundDetail> mutualFundDetailList;
    public List<MutualFundStatistic> mutualFundStatisticList;
    public List<Watchlist> watchlistList;

    @InjectMocks
    WatchlistController watchlistController;

    @Mock
    WatchlistService watchlistService;

    @Mock
    CompanyService companyService;

    @Before
    public void setUp(){
        userList = new ArrayList<>();
        companyDetailList = new ArrayList<>();
        stockDetailList = new ArrayList<>();
        stockStatisticList = new ArrayList<>();
        mutualFundDetailList = new ArrayList<>();
        mutualFundStatisticList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-00000000000"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth",Gender.MALE,"dpUrl"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000001"),"name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        stockDetailList.add(new StockDetail(UUID.fromString("00000000-0000-0000-0000-00000000002"), LocalDateTime.now(),"nemish",companyDetailList.get(0)));
        stockStatisticList.add(new StockStatistic(UUID.fromString("00000000-0000-0000-0000-00000000003"), 1000,  10.2f, 2.03f, 3.65f, 20.16f, 56.02f, 45.99f, 78.69f, 100.98f,stockDetailList.get(0)));
        mutualFundDetailList.add(new MutualFundDetail(UUID.fromString("00000000-0000-0000-0000-00000000004"),LocalDateTime.now(),"nemish", companyDetailList.get(0)));
        mutualFundStatisticList.add(new MutualFundStatistic(UUID.fromString("00000000-0000-0000-0000-00000005"), "high" , 1.0f, true, 25.36f, 98.36f, LocalDateTime.now(), 45.2f,mutualFundDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000006"),userList.get(0),companyDetailList.get(0)));
    }

    @Test
    public void displayStockWatchlist() {
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-00000000");
        List<StockWatchlistDTO> testStockWatchlistDTOList = new ArrayList<>();
        testStockWatchlistDTOList.add(new StockWatchlistDTO(companyDetailList.get(0).getName(),0.0f,0.0f,0.0f,0.0f));

        //When user have stock watchlist
        when(watchlistService.getAllWatchlistDetailByUserId(userUuid)).thenReturn(watchlistList);
        when(companyService.getCompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000001"))).thenReturn(java.util.Optional.of(companyDetailList.get(0)));
        List<StockWatchlistDTO> controllerStockWatchlistDTOList = watchlistController.displayStockWatchlist("00000000-0000-0000-0000-00000000");
        assertEquals(controllerStockWatchlistDTOList,testStockWatchlistDTOList);

        //When user don't have stock watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-999999999999");
        when(watchlistService.getAllWatchlistDetailByUserId(userUuid)).thenReturn(new ArrayList<Watchlist>());
        controllerStockWatchlistDTOList = watchlistController.displayStockWatchlist("00000000-0000-0000-0000-999999999999");
        assertEquals(controllerStockWatchlistDTOList,new ArrayList<StockWatchlistDTO>());

        //When user passes invalid UUID
        try{
            watchlistController.displayStockWatchlist("11111111");
        }catch (Exception e){
            assertTrue(e.getMessage().contains("Invalid Input"));
        }
    }
}