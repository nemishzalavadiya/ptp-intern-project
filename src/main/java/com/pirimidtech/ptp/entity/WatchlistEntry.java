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
    private UUID id;

    @ManyToOne
    private Watchlist watchlist;

    @ManyToOne
    private AssetDetail assetDetail;
}
