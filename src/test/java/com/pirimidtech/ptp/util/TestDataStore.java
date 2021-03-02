package com.pirimidtech.ptp.util;

import com.pirimidtech.ptp.entity.AssetClass;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.entity.Gender;
import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.entity.Watchlist;
import com.pirimidtech.ptp.entity.WatchlistEntry;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    public TestDataStore() {
        userList = new ArrayList<>();
        assetDetailList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        watchlistEntryList = new ArrayList<>();
        pageable = PageRequest.of(0, 10);
        userUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        userUuid2 = UUID.fromString("00000000-0000-0000-0000-999999999999");
        assetUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        assetUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        userList.add(new User(userUuid1, "Nemish", "email", "panCard", "mobileNo", "signature", "dataOfBirth", Gender.MALE, "dpUrl"));
        userList.add(new User(userUuid2, "Nemish", "email", "panCard", "mobileNo", "signature", "dataOfBirth", Gender.MALE, "dpUrl"));
        assetDetailList.add(new AssetDetail(assetUuid1, "name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        assetDetailList.add(new AssetDetail(assetUuid2, "name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000006"), userList.get(0), "myFirst", "description"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000007"), userList.get(0), "myFirst", "description"));
        watchlistEntryList.add(new WatchlistEntry(UUID.fromString("00000000-0000-0000-0000-000000000008"), watchlistList.get(0), assetDetailList.get(0)));
    }
}
