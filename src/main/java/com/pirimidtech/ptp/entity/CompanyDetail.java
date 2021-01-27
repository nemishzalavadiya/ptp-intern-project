package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companyDetail")
public class CompanyDetail {

    @Id
    public UUID companyId;

    private String name;

    private String logoUrl;

    private String assetClass;

    private String about;

    private String managingDirector;

    private String organization;

    @OneToMany(mappedBy = "companyDetail")
    private List<StockDetail> stockDetails;


    @OneToMany(mappedBy = "companyDetail")
    private List<MutualFundDetail> mutualFundDetails;

    @OneToMany(mappedBy = "companyDetail")
    private List<Position> positions;
}

