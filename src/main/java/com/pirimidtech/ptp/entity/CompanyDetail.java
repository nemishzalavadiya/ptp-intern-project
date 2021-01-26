package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companyDetail")
public class CompanyDetail {

    @Id
    public UUID companyID;

    private String name;

    private String logoURL;

    private String assetClass;

    private String about;

    private String managingDirector;

    private String organization;

    @OneToMany()
    private List<StockDetail> stockDetailList;


}
