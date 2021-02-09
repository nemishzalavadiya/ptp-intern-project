package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchlist")
public class Watchlist {
    @Id
    private UUID id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}
