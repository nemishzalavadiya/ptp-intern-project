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
@Table(name = "stockPrice")
public class StockPrice {

    @Id
    private UUID stockPirceId;

    private float  price;

    private LocalDateTime timestamp;

    private char stockExchange;// BSE or NSE

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn( name = "stockId")
    private StockDetail stockDetail;


}
