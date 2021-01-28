package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private UUID stockOrderID;

    private LocalDateTime timestamp;

    private Integer tradeVolume;

    @Enumerated(EnumType.STRING)
    private Action action;

    @Enumerated(EnumType.STRING)
    private StockExchangeType stockExchange;

    @Enumerated(EnumType.STRING)
    private PriceType priceType ;

    @Enumerated(EnumType.STRING)
    private OrderType orderType ;

    private Float price;

    private String status;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(name = "stockId")
    private StockDetail stockDetail;
}
