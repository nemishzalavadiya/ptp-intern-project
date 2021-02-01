package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchList")
public class Watchlist {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "watchList")
    private List<CompanyDetail> companyDetailList;
}
