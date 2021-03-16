package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchlistEntry")
public class WatchlistEntry {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Watchlist watchlist;

    @ManyToOne
    private AssetDetail assetDetail;
}
