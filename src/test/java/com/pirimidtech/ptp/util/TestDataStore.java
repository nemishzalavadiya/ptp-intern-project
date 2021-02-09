package com.pirimidtech.ptp.util;

import com.pirimidtech.ptp.entity.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataStore {
    public List<User> userList;
    public List<CompanyDetail> companyDetailList;
    public List<Watchlist> watchlistList;
    public Pageable pageable;

    public TestDataStore(){
        userList = new ArrayList<>();
        companyDetailList = new ArrayList<>();
        watchlistList = new ArrayList<>();
        pageable = PageRequest.of(0,10);
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-000000000000"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth", Gender.MALE,"dpUrl"));
        userList.add(new User(UUID.fromString("00000000-0000-0000-0000-000000000001"),"Nemish","email","panCard","mobileNo","signature","dataOfBirth", Gender.MALE,"dpUrl"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-000000000001"),"name", "logo_url", AssetClass.STOCK, "about", "nemish", "org"));
        companyDetailList.add(new CompanyDetail(UUID.fromString("00000000-0000-0000-0000-000000000010"),"name", "logo_url", AssetClass.MUTUAL_FUND, "about", "mohit", "org"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000006"),userList.get(0),"myFirst","description"));
        watchlistList.add(new Watchlist(UUID.fromString("00000000-0000-0000-0000-000000000007"),userList.get(0),"myFirst","description"));
    }
}
