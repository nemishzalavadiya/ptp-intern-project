package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "stockPrice")
public class StockPrice {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private float price;

    private Date timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockExchangeType stockExchange;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(nullable = false)
    private StockDetail stockDetail;
}
