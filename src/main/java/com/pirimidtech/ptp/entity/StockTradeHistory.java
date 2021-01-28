package com.pirimidtech.ptp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockTradeHistory")
public class StockTradeHistory {

    @Id
    private UUID tradeId;

    @ManyToOne(targetEntity =  User.class)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(targetEntity =  StockDetail.class)
    @JoinColumn(name = "stockId")
    private StockDetail stockDetail;

    private LocalDateTime timestamp;

    private Integer tradeVolume;

    private char stockExchange; //BSE or NSE

    private float price;

}
