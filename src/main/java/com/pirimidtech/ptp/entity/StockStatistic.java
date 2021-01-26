package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stockStatistic")
public class StockStatistic {
    @Id
    public UUID stockID;

    private Integer numberOfStackHolders;

    private float pbRatio;

    private float peRatio;

    private float industryPE;

    private float divYield;

    private float bookValue;

    private float marketCap;

    private float returnOnEquity;

    private float earningPerShareTTM;

//    @OneToOne(targetEntity = StockDetail.class)
//    @JoinColumn(name = "stockID")
    @OneToOne(targetEntity = StockDetail.class)
    @JoinColumn(name="stockID")
    @MapsId
    private StockDetail stockDetail;


}
