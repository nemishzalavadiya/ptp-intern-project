package com.pirimidtech.ptp;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.repository.WatchListRepository;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
        userList= new ArrayList<User>(){{
            add(new User(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000000"));
                setDateOfBirth("10/10/2000");
                setEmail("dummy@gamil.com");
                setDpURL("dpUrl");
                setGender(Gender.MALE);
                setMobileNo("9879876543");
                setPanCard("Pan_card_number");
                setSignatureUrl("signatureUrl");
                setName("Nemish");
            }});
        }};
        companyDetailList= new ArrayList<CompanyDetail>(){{
            add(new CompanyDetail(){{
                setName("TCS Stock");
                setId(UUID.fromString("00000000-0000-0000-0000-00000001"));
                setAbout("tata consultancy services stock");
                setAssetClass(AssetClass.STOCK);
                setLogoUrl("LogoUrl");
                setManagingDirector("Harsh Desai");
                setOrganization("Tata Group");
            }});
            add(new CompanyDetail(){{
                setName("TCS Mutual Fund");
                setId(UUID.fromString("00000000-0000-0000-0000-00000002"));
                setAbout("tata consultancy services mutual funs");
                setAssetClass(AssetClass.MUTUAL_FUND);
                setLogoUrl("LogoUrl");
                setManagingDirector("Mohit Nankani");
                setOrganization("Tata Group");
            }});
            add(new CompanyDetail(){{
                setName("Infosys Stock");
                setId(UUID.fromString("00000000-0000-0000-0000-00000003"));
                setAbout("Infosys consultancy services");
                setAssetClass(AssetClass.STOCK);
                setLogoUrl("LogoUrl");
                setManagingDirector("Nemish Zalavadiya");
                setOrganization("Infosys Group");
            }});
            add(new CompanyDetail(){{
                setName("Infosys Mutual Fund");
                setId(UUID.fromString("00000000-0000-0000-0000-00000004"));
                setAbout("Infosys consultancy services");
                setAssetClass(AssetClass.MUTUAL_FUND);
                setLogoUrl("LogoUrl");
                setManagingDirector("Darshan Gohel");
                setOrganization("Infosys Group");
            }});
        }};
        stockDetailList= new ArrayList<StockDetail>(){{
            add(new StockDetail(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000005"));
                setManagingDirector("Harsh Desai");
                setYearFounded(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(0));
            }});
            add(new StockDetail(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000006"));
                setManagingDirector("Nemish Zalavadiya");
                setYearFounded(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(2));
            }});
        }};
        stockStatisticList= new ArrayList<StockStatistic>(){{
            add(new StockStatistic(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000009"));
                setBookValue(30f);
                setDivYield(100.5f);
                setPbRatio(10.2f);
                setEarningPerShareTTM(100.4f);
                setMarketCap(1000.5f);
                setReturnOnEquity(10.6f);
                setPeRatio(11.4f);
                setIndustryPE(108.3f);
                setNumberOfStackHolders(1000);
                setStockDetail(stockDetailList.get(0));
            }});
            add(new StockStatistic(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000010"));
                setBookValue(130f);
                setDivYield(10.5f);
                setPbRatio(10.2f);
                setEarningPerShareTTM(100.4f);
                setMarketCap(1000.5f);
                setReturnOnEquity(10.6f);
                setPeRatio(11.4f);
                setIndustryPE(108.3f);
                setNumberOfStackHolders(100);
                setStockDetail(stockDetailList.get(1));
            }});
        }};
        mutualFundDetailList= new ArrayList<MutualFundDetail>(){{
            add(new MutualFundDetail(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000007"));
                setFundManager("Mohit Nankani");
                setLaunchDate(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(1));
            }});
            add(new MutualFundDetail(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000008"));
                setFundManager("Darshan Gohel");
                setLaunchDate(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(3));
            }});
        }};
        mutualFundStatisticList= new ArrayList<MutualFundStatistic>(){{
            add(new MutualFundStatistic(){{
                setMutualFundDetail(mutualFundDetailList.get(0));
                setId(UUID.fromString("00000000-0000-0000-0000-00000011"));
                setExpenseRatio(10.3f);
                setRisk("Low");
                setFundSize(100f);
                setFundStarted(LocalDateTime.now());
                setSipAllowed(true);
                setNav(12.8f);
                setMinSIP(100f);
            }});
            add(new MutualFundStatistic(){{
                setMutualFundDetail(mutualFundDetailList.get(1));
                setId(UUID.fromString("00000000-0000-0000-0000-00000012"));
                setExpenseRatio(14.3f);
                setRisk("Moderate");
                setFundSize(100f);
                setFundStarted(LocalDateTime.now());
                setSipAllowed(true);
                setNav(12.8f);
                setMinSIP(100f);
            }});
        }};
        watchlistList= new ArrayList<Watchlist>(){{
            add(new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(0));
            }});
            add(new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(1));
            }});
            add(new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(2));
            }});
            add(new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(3));
            }});
        }};
    }

    @Test
    public void getAllWatchlistDetailByUserId(){
        UUID userId = UUID.fromString("00000000-0000-0000-0000-00000000");
        List<Watchlist> filteredWatchlist = new ArrayList<>();
        watchlistList.forEach((item->{
            if(item.getUser().getId().compareTo(userId)==0){
                filteredWatchlist.add(item);
            }
        }));
        when(watchListRepository.findByUserId(userId)).thenReturn(filteredWatchlist);
        assertEquals(watchlistService.getAllWatchlistDetailByUserId(userId),watchlistList);
    }

}
