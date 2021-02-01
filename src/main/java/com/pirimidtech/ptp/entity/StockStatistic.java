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
    @MapsId
    private StockDetail stockDetail;
}