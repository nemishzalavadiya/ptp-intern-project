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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID mutualFundID;

    private Date launchDate;

//    @OneToOne(mappedBy = "mutualFundDetail")
    @OneToOne
    private MutualFundStatistic mutualFundStatistic;

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    private CompanyDetail companyDetail;

    @ManyToMany(mappedBy = "mutualFundDetail")
    private List<MutualFundCategory> mutualFundCategory;

}
