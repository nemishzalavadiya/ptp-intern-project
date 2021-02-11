package com.pirimidtech.ptp.utility;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.InvestmentType;
import com.pirimidtech.ptp.entity.MutualFundDetail;
import com.pirimidtech.ptp.entity.MutualFundOrder;
import com.pirimidtech.ptp.entity.MutualFundPrice;
import com.pirimidtech.ptp.entity.MutualFundStatistic;
import com.pirimidtech.ptp.entity.OrderType;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.entity.PriceType;
import com.pirimidtech.ptp.entity.Status;
import com.pirimidtech.ptp.entity.StockDetail;
import com.pirimidtech.ptp.entity.StockExchangeType;
import com.pirimidtech.ptp.entity.StockTrade;
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

    public static User user = new User(UUID.fromString("00000000-0000-0000-0000-000000000000"),"XYZ","email","panCard","mobileNo","signature","dataOfBirth",Gender.MALE,"dpUrl");
    public static AssetDetail assetDetail = new AssetDetail(UUID.fromString("2dde9b84-f692-4ceb-9686-1268bb771360"),"name", "logo_url",AssetClass.STOCK  ,"about", "XYZ", "org");
    public static AssetDetail assetDetail1 = new AssetDetail(UUID.fromString("51381618-1bc9-4c19-aab9-44994433b185"),"name", "logo_url",AssetClass.MUTUAL_FUND  ,"about", "XYZ", "org");

    public static MutualFundDetail mutualFundDetail = new MutualFundDetail(UUID.fromString("51381618-1bc9-4c19-aab9-000000000001"), null,"ABC", assetDetail1);

    public static MutualFundOrder mutualFundOrder1 = new MutualFundOrder(UUID.fromString("06f51c63-14f3-4c34-be12-ec1abd26c957"),null,100f, InvestmentType.MONTHLY_SIP,mutualFundDetail,user);
    public static MutualFundOrder mutualFundOrder2 = new MutualFundOrder(UUID.fromString("f0108f8a-0d7c-4235-bce3-5deda9a3e151"),null,105f, InvestmentType.MONTHLY_SIP,mutualFundDetail,user);

    public static List<MutualFundOrder> mutualFundOrderList = new ArrayList<>();

    public static MutualFundPrice mutualFundPrice1 = new MutualFundPrice(UUID.fromString("06f51c63-14f3-4c34-be12-ec1abd26c957"), 101.5F,null,mutualFundDetail);
    public static MutualFundPrice mutualFundPrice2 = new MutualFundPrice(UUID.fromString("f0108f8a-0d7c-4235-bce3-5deda9a3e151"), 11.5F,null,mutualFundDetail);

    public static MutualFundStatistic mutualFundStatistic = new MutualFundStatistic(UUID.fromString("95d9a220-2579-4a13-b7e2-7ac628ed2e6f"), "Low" , 0.0f, true, 0.0f, 0.0f, null, 0.0f,mutualFundDetail);
    public static Position position = new Position(UUID.fromString("06f51c63-14f3-4c34-be12-ec1abd26c957"),100,11.1f,AssetClass.STOCK,user, assetDetail);
    public static StockDetail stockDetail = new StockDetail(UUID.fromString("2ffedff5-70c5-45cd-9c35-b36c25d77361"), null,"nemish", assetDetail);

    public static StockTrade stockTrade1 = new StockTrade(UUID.fromString("06f51c63-14f3-4c34-be12-ec1abd26c957"),null, 301, Action.BUY, StockExchangeType.BSE, PriceType.MARKET, OrderType.DELIVERY, mutualFundOrder1.getPrice(), Status.EXECUTED, user, stockDetail);
    public static StockTrade stockTrade2 = new StockTrade(UUID.fromString("981a7cd2-feaf-4e29-8a77-f37ed75a6102"), null, 207, Action.BUY, StockExchangeType.NSE, PriceType.MARKET, OrderType.DELIVERY, mutualFundOrder1.getPrice(), Status.PENDING, user, stockDetail);

    public static List<StockTrade> stockTradeList = new ArrayList<>();
    public static StockTradeHistory stockTradeHistory = new StockTradeHistory(UUID.randomUUID(), LocalDateTime.now(),Status.PENDING, stockTrade1);

    public static StockPrice stockPrice1 = new StockPrice(UUID.fromString("06f51c63-14f3-4c34-be12-ec1abd26c957"), mutualFundOrder1.getPrice(), null, StockExchangeType.BSE, stockDetail);
    public static StockPrice stockPrice2 = new StockPrice(UUID.fromString("981a7cd2-feaf-4e29-8a77-f37ed75a6102"), mutualFundOrder1.getPrice(), null, StockExchangeType.BSE, stockDetail);

    public static StockStatistic stockStatistic = new StockStatistic(UUID.fromString("981a7cd2-feaf-4e29-8a77-f37ed75a6102"), 12,10,11,12,52.2f, 12.3f,14.3f,15,32,stockDetail);

    public  static Watchlist watchlist = new Watchlist(UUID.fromString("18d8c801-ec46-43c1-8295-edbf0d1aa406"),user, assetDetail);

}
