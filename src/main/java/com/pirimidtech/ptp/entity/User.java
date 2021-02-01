package com.pirimidtech.ptp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="userDetail")
public class User {
    @Id
    private UUID id;

    private String name;

    private String email;

    private String panCard;

    private String mobileNo;

    private String signatureUrl;

    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String dpURL;

    @OneToMany(mappedBy = "user")
    private List<StockOrder> stockOrderList;

    @OneToMany(mappedBy = "user")
    private List<MutualFundOrder> MutualFundOrderList;

    @OneToMany(mappedBy = "user")
    private  List<StockTradeHistory> stockTradeHistoryList;

    @OneToMany(mappedBy = "user")
    private  List<Position> positionList;

    @OneToOne(mappedBy = "user")
    private Watchlist watchlist;
}

