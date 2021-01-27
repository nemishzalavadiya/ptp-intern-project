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
    private UUID companyID;
    private String name;
    private String logoURL;
    private String assetClass;
    private String about;
    private String managingDirector;
    private String organization;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "companyDetail")
    private List<StockDetail> stockDetailList;


    @OneToMany(mappedBy = "companyDetail")
    private List<Position> positionList;

    @OneToMany(mappedBy = "companyDetail")
    private List<MutualFundDetail> mutualFundDetails;


}
