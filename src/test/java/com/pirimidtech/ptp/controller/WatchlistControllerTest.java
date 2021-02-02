package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.*;
import com.pirimidtech.ptp.repository.CompanyDetailRepository;
import com.pirimidtech.ptp.service.company.CompanyService;
import com.pirimidtech.ptp.service.mutualfund.MutualFundService;
import com.pirimidtech.ptp.service.stock.StockService;
import com.pirimidtech.ptp.service.watchlist.WatchlistService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WatchlistControllerTest {

    public static List<User> userList;
    public static List<CompanyDetail> companyDetailList;
    public static List<StockDetail> stockDetailList;
    public static List<StockStatistic> stockStatisticList;
    public static List<MutualFundDetail> mutualFundDetailList;
    public static List<MutualFundStatistic> mutualFundStatisticList;
    public static List<Watchlist> watchlistList;

    @InjectMocks
    WatchlistController watchlistController= new WatchlistController();

    @Mock
    private WatchlistService watchlistService;

    @Mock
    private CompanyService companyService;

    @Mock
    private StockService stockService;

    @Mock
    private MutualFundService mutualFundService;

    @Mock
    CompanyDetailRepository companyDetailRepository;

    @BeforeAll
    public static void setUp(){
        userList= new ArrayList<User>(){{
            new User(){{
                setId(UUID.fromString("00000000-0000-0000-0000-00000000"));
                setDateOfBirth("10/10/2000");
                setEmail("dummy@gamil.com");
                setDpURL("dpUrl");
                setGender(Gender.MALE);
                setMobileNo("9879876543");
                setPanCard("Pan_card_number");
                setSignatureUrl("signatureUrl");
                setName("Nemish");
            }};
        }};
        companyDetailList= new ArrayList<CompanyDetail>(){{
            new CompanyDetail(){{
                setName("TCS Stock");
                setId(UUID.fromString("00000000-0000-0000-0000-00000001"));
                setAbout("tata consultancy services stock");
                setAssetClass(AssetClass.STOCK);
                setLogoUrl("LogoUrl");
                setManagingDirector("Harsh Desai");
                setOrganization("Tata Group");
            }};
            new CompanyDetail(){{
                setName("TCS Mutual Fund");
                setId(UUID.fromString("00000000-0000-0000-0000-00000002"));
                setAbout("tata consultancy services mutual funs");
                setAssetClass(AssetClass.MUTUAL_FUND);
                setLogoUrl("LogoUrl");
                setManagingDirector("Mohit Nankani");
                setOrganization("Tata Group");
            }};
            new CompanyDetail(){{
                setName("Infosys Stock");
                setId(UUID.fromString("00000000-0000-0000-0000-00000003"));
                setAbout("Infosys consultancy services");
                setAssetClass(AssetClass.STOCK);
                setLogoUrl("LogoUrl");
                setManagingDirector("Nemish Zalavadiya");
                setOrganization("Infosys Group");
            }};
            new CompanyDetail(){{
                setName("Infosys Mutual Fund");
                setId(UUID.fromString("00000000-0000-0000-0000-00000004"));
                setAbout("Infosys consultancy services");
                setAssetClass(AssetClass.MUTUAL_FUND);
                setLogoUrl("LogoUrl");
                setManagingDirector("Darshan Gohel");
                setOrganization("Infosys Group");
            }};
        }};
        stockDetailList= new ArrayList<StockDetail>(){{
            new StockDetail(){{
                setId(UUID.fromString("00000000-0000-0000-00000005"));
                setManagingDirector("Harsh Desai");
                setYearFounded(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(0));
            }};
            new StockDetail(){{
                setId(UUID.fromString("00000000-0000-0000-00000006"));
                setManagingDirector("Nemish Zalavadiya");
                setYearFounded(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(2));
            }};
        }};
        stockStatisticList= new ArrayList<StockStatistic>(){{
            new StockStatistic(){{
                setId(UUID.fromString("00000000-0000-0000-00000009"));
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
            }};
            new StockStatistic(){{
                setId(UUID.fromString("00000000-0000-0000-00000010"));
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
            }};
        }};
        mutualFundDetailList= new ArrayList<MutualFundDetail>(){{
            new MutualFundDetail(){{
                setId(UUID.fromString("00000000-0000-0000-00000007"));
                setFundManager("Mohit Nankani");
                setLaunchDate(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(1));
            }};
            new MutualFundDetail(){{
                setId(UUID.fromString("00000000-0000-0000-00000008"));
                setFundManager("Darshan Gohel");
                setLaunchDate(LocalDateTime.now());
                setCompanyDetail(companyDetailList.get(3));
            }};
        }};
        mutualFundStatisticList= new ArrayList<MutualFundStatistic>(){{
            new MutualFundStatistic(){{
                setMutualFundDetail(mutualFundDetailList.get(0));
                setId(UUID.fromString("00000000-0000-0000-00000011"));
                setExpenseRatio(10.3f);
                setRisk("Low");
                setFundSize(100f);
                setFundStarted(LocalDateTime.now());
                setSipAllowed(true);
                setNav(12.8f);
                setMinSIP(100f);
            }};
            new MutualFundStatistic(){{
                setMutualFundDetail(mutualFundDetailList.get(1));
                setId(UUID.fromString("00000000-0000-0000-00000012"));
                setExpenseRatio(14.3f);
                setRisk("Moderate");
                setFundSize(100f);
                setFundStarted(LocalDateTime.now());
                setSipAllowed(true);
                setNav(12.8f);
                setMinSIP(100f);
            }};
        }};
        watchlistList= new ArrayList<Watchlist>(){{
            new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(0));
            }};
            new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(1));
            }};
            new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(2));
            }};
            new Watchlist(){{
                setId(UUID.fromString("00000000-0000-0000-00000013"));
                setUser(userList.get(0));
                setCompanyDetail(companyDetailList.get(3));
            }};
        }};
    }

    @Test
    void displayStockWatchlist() {
    }

    @Test
    void displayMutualFundWatchlist() {
    }

    @Test
    void searchNameLike() {
    }

    @Test
    void addCompany() {
    }

    @Test
    void removeCompany() {
    }
}