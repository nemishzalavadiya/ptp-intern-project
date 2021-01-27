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
@Table(name = "stockBrand")
public class StockBrands {
    @Id
    public UUID brandId;
    private String brandName;
    private String brandLogoUrl;
    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn(name="stockId")
    private StockDetail stockDetail;
}