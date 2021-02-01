package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "stockPrice")
public class StockPrice {
    @Id
    private UUID id;

    private float  price;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private StockExchangeType stockExchange;

    @ManyToOne(targetEntity = StockDetail.class)
    private StockDetail stockDetail;
}
