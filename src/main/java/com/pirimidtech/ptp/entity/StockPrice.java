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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "stockPrice")
public class StockPrice {
    public enum StockExchangeType{
        BSE,
        NSE
    }
    @Id
    private UUID stockPriceId;

    private float  price;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private StockExchangeType stockExchange;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn( name = "stockId")
    private StockDetail stockDetail;
}
