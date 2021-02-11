package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockDetail")
public class StockDetail {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime yearFounded;

    private String managingDirector;

    @OneToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}