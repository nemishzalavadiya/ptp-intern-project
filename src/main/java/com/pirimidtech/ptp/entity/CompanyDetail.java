package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companyDetail")
public class CompanyDetail {
    @Id
    private UUID id;

    private String name;

    private String logoUrl;

    private String assetClass;

    private String about;

    private String managingDirector;

    private String organization;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @OneToOne
    private StockDetail stockDetail;

    @OneToMany(mappedBy = "companyDetail")
    private List<Position> positionList;

    @OneToOne
    private MutualFundDetail mutualFundDetail;

    @OneToOne(mappedBy = "companyDetail")
    private Watchlist watchlist;
}
