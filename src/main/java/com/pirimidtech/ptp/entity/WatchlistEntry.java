package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(nullable = false)
    private Watchlist watchlist;

    @ManyToOne
    @Column(nullable = false)
    private AssetDetail assetDetail;
}
