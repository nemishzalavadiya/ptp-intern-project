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
    public enum Sector{
        BANKING,
        FINANCIAL,
        AGRICULTURAL,
        AUTOMOBILE,
        etc
    }
    @Id
    private UUID companyID;

    private String name;

    private String logoURL;

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

    @ManyToOne(targetEntity = WatchList.class)
    private WatchList watchList;
}
