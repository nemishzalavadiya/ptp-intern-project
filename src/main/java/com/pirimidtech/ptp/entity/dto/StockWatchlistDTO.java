package com.pirimidtech.ptp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockWatchlistDTO {
    private String name;

    private Float tradePrice;

    private Float percentageChange;

    private Float openPrice;

    private Float closePrice;
}
