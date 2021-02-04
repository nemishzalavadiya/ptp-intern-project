package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.repository.WatchListRepository;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceMockTest {

    public List<User> userList;
    public List<CompanyDetail> companyDetailList;
    public List<StockDetail> stockDetailList;
    public List<StockStatistic> stockStatisticList;
    public List<MutualFundDetail> mutualFundDetailList;
    public List<MutualFundStatistic> mutualFundStatisticList;
    public List<Watchlist> watchlistList;

    @InjectMocks
    WatchlistService watchlistService;

    @Mock
    WatchListRepository watchListRepository;

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
    public void getAllWatchlistDetailByUserId(){
        //When user do have watchlist
        UUID userId = UUID.fromString("00000000-0000-0000-0000-00000000");
        List<Watchlist> filteredWatchlist = new ArrayList<>();
        UUID finalUserId = userId;
        watchlistList.forEach((item->{
            if(item.getUser().getId().compareTo(finalUserId)==0){
                filteredWatchlist.add(item);
            }
        }));
        when(watchListRepository.findByUserId(userId)).thenReturn(filteredWatchlist);
        assertEquals(watchlistService.getAllWatchlistDetailByUserId(userId),filteredWatchlist);

        //When user don't have watchlist
        userId = UUID.fromString("00000000-0000-0000-0000-999999999999");
        when(watchListRepository.findByUserId(userId)).thenReturn(new ArrayList<>());
        when(watchListRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

    }
    @Test
    void add() {
        CompanyDetail companyDetail = new CompanyDetail();
        companyDetail.setId(UUID.randomUUID());
        Watchlist watchlist = new Watchlist(UUID.randomUUID(),
                new User(UUID.fromString("e6747fcc-1351-44f8-99ea-e5be3de8464e"),"abc","abc@dev.com","","","","", Gender.MALE,""),
                companyDetail);
        watchlistService.add(watchlist);
        verify(watchListRepository,times(1)).save(watchlist);
    }

}
