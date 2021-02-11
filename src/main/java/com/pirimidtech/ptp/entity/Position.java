package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue
    private UUID id;

    private int volume;

    private float price;

    @Enumerated(EnumType.STRING)
    private AssetClass assetClass;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}
