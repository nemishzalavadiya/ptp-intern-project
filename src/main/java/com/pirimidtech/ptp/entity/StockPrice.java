package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
