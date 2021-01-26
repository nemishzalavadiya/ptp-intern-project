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
@Table(name = "stockBrands")
public class StockBrands {
    @Id
    public UUID brandID;

    private String brandName;

    private String brandLogoURL;

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(name = "stockID")
    private StockDetail stockDetail;


}
