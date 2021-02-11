package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;
import java.time.LocalDateTime;
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

    private LocalDateTime timestamp;

    private int tradeVolume;

    @Enumerated(EnumType.STRING)
    private Action action;

    @Enumerated(EnumType.STRING)
    private StockExchangeType stockExchange;

    @Enumerated(EnumType.STRING)
    private PriceType priceType ;

    @Enumerated(EnumType.STRING)
    private OrderType orderType ;

    private float price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = StockDetail.class)
    private StockDetail stockDetail;
}
