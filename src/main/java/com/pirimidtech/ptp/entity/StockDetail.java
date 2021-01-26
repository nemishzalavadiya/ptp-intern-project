package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stockDetail")
public class StockDetail {
    @Id
    public UUID stockID;

    @ManyToOne()
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    private CompanyDetail companyDetail;

    private Date yearFounded;

    private String managingDirector;

    @OneToOne()
    private StockStatistic stockStatistic;

    @OneToMany()
    private List<StockBrands> stockBrandsList;


}
