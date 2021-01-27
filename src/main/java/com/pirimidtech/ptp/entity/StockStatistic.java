package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockStatistic")
public class StockStatistic {
    @Id
    private UUID stockID;

    private Integer numberOfStackHolders;

    private Float pbRatio;

    private Float peRatio;

    private Float industryPE;

    private Float divYield;

    private Float bookValue;

    private Float marketCap;

    private Float returnOnEquity;

    private Float earningPerShareTTM;

    @OneToOne(targetEntity = StockDetail.class)
    @JoinColumn(name="stockID")
    @MapsId
    private StockDetail stockDetail;
}