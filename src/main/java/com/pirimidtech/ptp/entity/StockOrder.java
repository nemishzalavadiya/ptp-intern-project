package com.pirimidtech.ptp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stockOrder")
public class StockOrder {

    @Id
    @Column
    public UUID orderID;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(name = "stockId")
    private StockDetail stockDetail;


    @Column
    private Date timestamp;
    @Column
    private Integer   tradeVolume;
    @Column
    private char isSellOrBuy;
    @Column
    private char   stockExchange;
    @Column
    private char priceType ;//    market/Limit
    @Column
    private char orderType ;//delivery/intraDay
    @Column
    private float   price;
    @Column
    private String status;

}
