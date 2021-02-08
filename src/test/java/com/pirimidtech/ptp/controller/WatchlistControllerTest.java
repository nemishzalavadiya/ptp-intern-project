package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.entity.dto.MutualFundWatchlistDTO;
import com.pirimidtech.ptp.entity.dto.StockWatchlistDTO;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Mock
    MutualFundService mutualFundService;
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
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000010"),"name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org"));
        stockDetailList.add(new StockDetail(UUID.fromString("00000000-0000-0000-0000-00000000002"), LocalDateTime.now(),"nemish",companyDetailList.get(0)));
        stockStatisticList.add(new StockStatistic(UUID.fromString("00000000-0000-0000-0000-00000000003"), 1000,  10.2f, 2.03f, 3.65f, 20.16f, 56.02f, 45.99f, 78.69f, 100.98f,stockDetailList.get(0)));
        mutualFundDetailList.add(new MutualFundDetail(UUID.fromString("00000000-0000-0000-0000-00000000004"),LocalDateTime.now(),"nemish", companyDetailList.get(1)));
        mutualFundStatisticList.add(new MutualFundStatistic(UUID.fromString("00000000-0000-0000-0000-00000005"), "Low" , 0.0f, true, 0.0f, 0.0f, LocalDateTime.now(), 0.0f,mutualFundDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000006"),userList.get(0),companyDetailList.get(0)));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000007"),userList.get(0),companyDetailList.get(1)));
    }
    @Test
    public void displayStockWatchlist() {
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-00000000");
        List<StockWatchlistDTO> testStockWatchlistDTOList = new ArrayList<>();
        testStockWatchlistDTOList.add(new StockWatchlistDTO(companyDetailList.get(0).getName(),0.0f,0.0f,0.0f,0.0f));
        //When user have stock watchlist
        Pageable pageable= PageRequest.of(0,10);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.STOCK,pageable)).thenReturn(watchlistList.subList(0,1));
        when(companyService.getCompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000001"))).thenReturn(Optional.of(companyDetailList.get(0)));
        List<StockWatchlistDTO> controllerStockWatchlistDTOList = watchlistController.displayStockWatchlist("00000000-0000-0000-0000-00000000",0,10);
        assertEquals(controllerStockWatchlistDTOList,testStockWatchlistDTOList);
        //When user don't have stock watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-999999999999");
        pageable= PageRequest.of(0,10);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.STOCK,pageable)).thenReturn(new ArrayList<>());
        controllerStockWatchlistDTOList = watchlistController.displayStockWatchlist("00000000-0000-0000-0000-999999999999",0,10);
        assertEquals(controllerStockWatchlistDTOList,new ArrayList<StockWatchlistDTO>());
        //When user passes invalid UUID
        try{
            watchlistController.displayStockWatchlist("11111111",0,10);
            assertFalse(false);
        }catch (Exception e){
            assertTrue(e.getMessage().contains("Invalid Input"));
        }
    }
    @Test
    public void displayMutualFundWatchlist() {
        UUID userUuid = UUID.fromString("00000000-0000-0000-0000-00000000");
        List<MutualFundWatchlistDTO> testMutualFundWatchlistDTOList = new ArrayList<>();
        testMutualFundWatchlistDTOList.add(new MutualFundWatchlistDTO(companyDetailList.get(0).getName(),"Low",0.0f,0.0f,0.0f));
        //When user have mutual fund watchlist
        Pageable pageable= PageRequest.of(0,10);
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.MUTUAL_FUND,pageable))
                .thenReturn(watchlistList.subList(1,2));
        when(companyService.getCompanyDetail(UUID.fromString("00000000-0000-0000-0000-0000010")))
                .thenReturn(Optional.of(companyDetailList.get(1)));
        when(mutualFundService.getMutualFundDetailByCompanyId(companyDetailList.get(1).getId())).thenReturn(Optional.of(mutualFundDetailList.get(0)));
        when(mutualFundService.getMutualFundStatsById(mutualFundDetailList.get(0).getId())).thenReturn(Optional.of(mutualFundStatisticList.get(0)));
        List<MutualFundWatchlistDTO> controllerMutualFundWatchlistDTOList = watchlistController.displayMutualFundWatchlist("00000000-0000-0000-0000-00000000",0,10);
        assertEquals(controllerMutualFundWatchlistDTOList,testMutualFundWatchlistDTOList);
        //When user don't have mutual fund watchlist
        userUuid = UUID.fromString("00000000-0000-0000-0000-999999999999");
        when(watchlistService.getWatchlistDetailByUserId(userUuid,AssetClass.MUTUAL_FUND,pageable)).thenReturn(new ArrayList<>());
        controllerMutualFundWatchlistDTOList = watchlistController.displayMutualFundWatchlist("00000000-0000-0000-0000-999999999999",0,10);
        assertEquals(controllerMutualFundWatchlistDTOList,new ArrayList<>());
        //When user passes invalid UUID
        try{
            watchlistController.displayMutualFundWatchlist("11111111",0,10);
            assertFalse(false);
        }catch (Exception e){
            assertTrue(e.getMessage().contains("Invalid Input"));
        }
    }
}