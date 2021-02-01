package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;

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

    @OneToMany(mappedBy = "companyDetail")
    private List<Position> positionList;
}
