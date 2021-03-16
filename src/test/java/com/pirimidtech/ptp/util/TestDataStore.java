package com.pirimidtech.ptp.util;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataStore {
    public List<User> userList;
    public List<AssetDetail> assetDetailList;
    public List<Watchlist> watchlistList;
    public List<WatchlistEntry> watchlistEntryList;
    public Pageable pageable;
    public UUID userUuid1, userUuid2, assetUuid1, assetUuid2;
    public HttpServletRequest httpServletRequest;


    public TestDataStore() {
        userList = new ArrayList<>();
        assetDetailList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        watchlistEntryList = new ArrayList<>();
        userUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        userUuid2 = UUID.fromString("00000000-0000-0000-0000-999999999999");
        assetUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        assetUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        userList.add(new User(userUuid1, "1234567812345678", "userName1", "userl", "encryptedPassword", "email", "panCard", "mobileNo", "signature", "dataOfBirth", false, Gender.MALE, "dpUrl"));
        userList.add(new User(userUuid2, "1234567812345678", "userName2", "userl", "encryptedPassword", "email", "panCard", "mobileNo", "signature", "dataOfBirth", true, Gender.MALE, "dpUrl"));
        pageable = PageRequest.of(0, 10);
        assetDetailList.add(new AssetDetail(assetUuid1, "name", "logo_url", AssetClass.STOCK, "about", "nemish", "org","HNGL"));
        assetDetailList.add(new AssetDetail(assetUuid2, "name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org","NMZL"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000006"), userList.get(0), "myFirst", "description"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000007"), userList.get(0), "myFirst", "description"));
        watchlistEntryList.add(new WatchlistEntry(UUID.fromString("00000000-0000-0000-0000-000000000008"), watchlistList.get(0), assetDetailList.get(0)));
    }
}
