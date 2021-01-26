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

    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyID")
    private CompanyDetail companyDetail;

    private Date yearFounded;

    private String managingDirector;

    //@OneToOne(mappedBy = "StockDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy ="stockDetail")
    @PrimaryKeyJoinColumn
    private StockStatistic stockStatistic;

    @OneToMany(mappedBy = "stockDetail")
    private List<StockBrands> stockBrandsList;


}
