package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private UUID userId;

    private String userName;

    @OneToMany(mappedBy = "user")
    private List<StockOrder> stockOrder;

    @OneToMany(mappedBy = "user")
    private List<MutualFundOrder> MutualFundOrderList;

    @OneToMany(mappedBy = "user")
    private  List<StockTradeHistory> stockTradeHistoryList;

    @OneToMany(mappedBy = "user")
    private  List<Position> positionList;

}

