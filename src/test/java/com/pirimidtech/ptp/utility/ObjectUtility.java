package com.pirimidtech.ptp.utility;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.CompanyDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.OrderType;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.PriceType;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.StockPrice;
import com.pirimidtech.ptp.entity.StockStatistic;
import com.pirimidtech.ptp.entity.StockTradeHistory;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.entity.Watchlist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ObjectUtility {

    public static User user = new User(UUID.fromString("00000000-0000-0000-0000-00000000010"),"XYZ","email","panCard","mobileNo","signature","dataOfBirth",Gender.MALE,"dpUrl");
    public static CompanyDetail companyDetail = new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-00000000001"),"name", "logo_url",AssetClass.STOCK  ,"about", "XYZ", "org");
    public static MutualFundDetail mutualFundDetail = new MutualFundDetail(UUID.fromString("00000000-0000-0000-0000-00000000002"), LocalDateTime.now(),"ABC", companyDetail);

    public static MutualFundOrder mutualFundOrder1 = new MutualFundOrder(UUID.fromString("00000000-0000-0000-0000-00000000003"),null,100f, InvestmentType.MONTHLY_SIP,mutualFundDetail,user);
    public static MutualFundOrder mutualFundOrder2 = new MutualFundOrder(UUID.fromString("00000000-0000-0000-0000-000000000012"),null,105f, InvestmentType.MONTHLY_SIP,mutualFundDetail,user);

    public static List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();

    public static MutualFundPrice mutualFundPrice1 = new MutualFundPrice(UUID.fromString("00000000-0000-0000-0000-00000000004"), 101.5F,LocalDateTime.now(),mutualFundDetail);
    public static MutualFundPrice mutualFundPrice2 = new MutualFundPrice(UUID.fromString("00000000-0000-0000-0000-00000000016"), 11.5F,LocalDateTime.now(),mutualFundDetail);

    public static MutualFundStatistic mutualFundStatistic = new MutualFundStatistic(UUID.fromString("00000000-0000-0000-0000-00000005"), "Low" , 0.0f, true, 0.0f, 0.0f, LocalDateTime.now(), 0.0f,mutualFundDetail);
    public static Position position = new Position(UUID.fromString("00000000-0000-0000-0000-00000005"),100,11.1f,AssetClass.STOCK,user,companyDetail);
    public static StockDetail stockDetail = new StockDetail(UUID.fromString("00000000-0000-0000-0000-00000000006"), LocalDateTime.now(),"XYZ",companyDetail);

    public static StockOrder stockOrder1 = new StockOrder(UUID.fromString("00000000-0000-0000-0000-00000000007"), LocalDateTime.now(), 301, Action.BUY, StockExchangeType.BSE, PriceType.MARKET, OrderType.DELIVERY, mutualFundOrder1.getPrice(), "Pending", user, stockDetail);
    public static StockOrder stockOrder2 = new StockOrder(UUID.fromString("00000000-0000-0000-0000-000000000013"), LocalDateTime.now(), 207, Action.BUY, StockExchangeType.NSE, PriceType.MARKET, OrderType.DELIVERY, mutualFundOrder1.getPrice(), "Pending", user, stockDetail);

    public static List<StockOrder> stockOrderList = new ArrayList<>();

    public static StockPrice stockPrice1 = new StockPrice(UUID.fromString("00000000-0000-0000-0000-00000000008"), mutualFundOrder1.getPrice(), LocalDateTime.now(), StockExchangeType.BSE, stockDetail);
    public static StockPrice stockPrice2 = new StockPrice(UUID.fromString("00000000-0000-0000-0000-00000000015"), mutualFundOrder1.getPrice(), LocalDateTime.now(), StockExchangeType.BSE, stockDetail);

    public static StockStatistic stockStatistic = new StockStatistic(UUID.fromString("00000000-0000-0000-0000-00000000006"), 12,10,11,12,52.2f, 12.3f,14.3f,15,32,stockDetail);

    public static StockTradeHistory stockTradeHistory1 = new StockTradeHistory(UUID.fromString("00000000-0000-0000-0000-00000000009"),LocalDateTime.now(), stockOrder1.getTradeVolume(), StockExchangeType.BSE, stockOrder1.getPrice(), stockOrder1.getUser(), stockDetail);
    public static StockTradeHistory stockTradeHistory2 = new StockTradeHistory(UUID.fromString("00000000-0000-0000-0000-00000000014"),LocalDateTime.now(), stockOrder1.getTradeVolume(), StockExchangeType.BSE, stockOrder1.getPrice(), stockOrder1.getUser(), stockDetail);

    public  static Watchlist watchlist = new Watchlist(UUID.fromString("00000000-0000-0000-0000-00000011"),user,companyDetail);

}
