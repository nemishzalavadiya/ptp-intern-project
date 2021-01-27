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
@Table(name = "mutualFundDetail")

public class MutualFundDetail {
    @Id
    private UUID mutualFundId;

    private Date launchDate;

    @OneToOne(mappedBy = "mutualFundDetail")
    private MutualFundStatistic mutualFundStatistic;

    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyId")
    private CompanyDetail companyDetail;

    @ManyToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundCategory> mutualFundCategories;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundPrice> mutualFundPrices;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundOrder> mutualFundOrders;
}
