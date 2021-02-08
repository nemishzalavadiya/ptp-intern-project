package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockStatistic")
public class StockStatistic {
    @Id
    private UUID id;

    private int numberOfStackHolders;

    private float pbRatio;

    private float peRatio;

    private float industryPE;

    private float divYield;

    private float bookValue;

    private float marketCap;

    private float returnOnEquity;

    private float earningPerShareTTM;

    @OneToOne(targetEntity = StockDetail.class)
    @MapsId
    private StockDetail stockDetail;
}
