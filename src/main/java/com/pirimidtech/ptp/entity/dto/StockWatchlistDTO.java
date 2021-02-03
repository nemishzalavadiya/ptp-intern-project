package com.pirimidtech.ptp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockWatchlistDTO {
    private String name;

    private float tradePrice;

    private float percentageChange;

    private float openPrice;

    private float closePrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockWatchlistDTO that = (StockWatchlistDTO) o;
        return Float.compare(that.tradePrice, tradePrice) == 0 && Float.compare(that.percentageChange, percentageChange) == 0 && Float.compare(that.openPrice, openPrice) == 0 && Float.compare(that.closePrice, closePrice) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tradePrice, percentageChange, openPrice, closePrice);
    }

}
