package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    private UUID id;

    private LocalDateTime yearFounded;

    private String managingDirector;

    @OneToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}