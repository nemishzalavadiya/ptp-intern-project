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
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue
    private UUID id;

    private float volume;

    private float price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClass assetClass;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;

    @OneToOne(targetEntity = AssetDetail.class)
    @JoinColumn(nullable = false)
    private AssetDetail assetDetail;
}
