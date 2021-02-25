package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundDetail")
public class MutualFundDetail {
    @Id
    @GeneratedValue
    private UUID id;

    private Date launchDate;

    private String fundManager;

    @OneToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}
