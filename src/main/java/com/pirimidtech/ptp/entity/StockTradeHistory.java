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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockTradeHistory")
public class StockTradeHistory {
    @Id
    private UUID id;

    private LocalDateTime timestamp;

    private Integer tradeVolume;

    @Enumerated(EnumType.STRING)
    private StockExchangeType stockExchangeType;

    private Float price;

    @ManyToOne(targetEntity =  User.class)
    private User user;

    @ManyToOne(targetEntity =  StockDetail.class)
    private StockDetail stockDetail;
}
