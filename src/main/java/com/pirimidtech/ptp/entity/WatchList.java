package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchList")
public class WatchList {
    @Id
    private UUID watchListID;

    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "watchList")
    private List<CompanyDetail> companyDetailList;
}
