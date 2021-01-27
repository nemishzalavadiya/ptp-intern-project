package com.pirimidtech.ptp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stockWatchlist")
public class StockWatchlist {
    @Id
    private UUID stockWatchlistID;

    @OneToMany(mappedBy = "stockWatchlist")
    private List<StockDetail> stockDetailList;

}
