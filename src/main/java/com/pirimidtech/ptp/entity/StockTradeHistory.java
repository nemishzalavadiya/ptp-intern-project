package com.pirimidtech.ptp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.auditing.DateTimeProvider;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockTradeHistory")
public class StockTradeHistory {

    @Id
    private UUID tradeId;

    @ManyToOne(targetEntity =  User.class)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(targetEntity =  StockDetail.class)
    @JoinColumn(name = "stockId")
    private StockDetail stockDetail;

    private LocalDateTime timestamp;

    private Integer tradeVolume;

    private char isBSE;

    private float price;

}
