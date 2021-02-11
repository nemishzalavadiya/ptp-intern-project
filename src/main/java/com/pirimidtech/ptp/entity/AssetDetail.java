package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assetDetail")
public class AssetDetail {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String logoUrl;

    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;

    private String about;

    private String managingDirector;

    private String organization;
}
