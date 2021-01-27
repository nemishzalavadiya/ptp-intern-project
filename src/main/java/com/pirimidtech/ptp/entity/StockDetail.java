package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.CascadeType;

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
    public UUID stockId;
    private Date yearFounded;
    private String managingDirector;
    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyId")
    private CompanyDetail companyDetail;

    //@OneToOne(mappedBy = "StockDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy ="stockDetail")
    @PrimaryKeyJoinColumn
    private StockStatistic stockStatistic;

    @OneToMany(mappedBy = "stockDetail")
    private List<StockBrands> stockBrands;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockPrice> stockPrices;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockOrder> stockOrders;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockTradeHistory> stockTradeHistories;


}