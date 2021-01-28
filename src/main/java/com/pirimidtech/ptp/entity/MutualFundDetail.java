package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundDetail")
public class MutualFundDetail {

    @Id
    private UUID mutualFundID;

    private LocalDateTime launchDate;

    private String fundManager;

    private Subcategory subcategory;

    @OneToOne(mappedBy = "mutualFundDetail")
    private MutualFundStatistic mutualFundStatistic;

    @OneToOne(mappedBy = "mutualFundDetail")
    private CompanyDetail companyDetail;

    @ManyToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundCategory> mutualFundCategory;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundPrice> mutualFundPriceList;

    @OneToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundOrder> mutualFundOrderList;
}
