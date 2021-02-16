package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockTrade")
public class StockTrade {
    @Id
    @GeneratedValue
    private UUID id;

    private Date timestamp;

    @Column(nullable = false)
    private int tradeVolume;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockExchangeType stockExchange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceType priceType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private OrderType orderType;

    @Column(nullable = false)
    private float price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(nullable = false)
    private StockDetail stockDetail;
}
