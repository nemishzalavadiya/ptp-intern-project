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
    private UUID mutualFundID;

    private Date launchDate;

    @OneToOne(mappedBy = "mutualFundDetail")
    private MutualFundStatistic mutualFundStatistic;

    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyID")
    private CompanyDetail companyDetail;

    @ManyToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundCategory> mutualFundCategory;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundPrice> mutualFundPriceList;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundOrder> mutualFundOrderList;
}
