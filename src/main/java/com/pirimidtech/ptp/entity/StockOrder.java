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
@Table(name = "stockOrder")

public class StockOrder {

    @Id
    private UUID orderId;

    private LocalDateTime timestamp;

    private Integer tradeVolume;

    private char sellOrBuy;

    private char stockExchange;

    private char priceType ;//    market/Limit

    private char orderType ;//delivery/intraDay

    private float price;

    private String status;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(name = "stockId")
    private StockDetail stockDetail;

}
