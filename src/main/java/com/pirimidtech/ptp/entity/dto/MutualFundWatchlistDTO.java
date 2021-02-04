package com.pirimidtech.ptp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundWatchlistDTO {
    public String name;

    public String risk;

    public float minSIP;

    public float expenseRatio;

    public float nav;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutualFundWatchlistDTO that = (MutualFundWatchlistDTO) o;
        return Float.compare(that.minSIP, minSIP) == 0 && Float.compare(that.expenseRatio, expenseRatio) == 0 && Float.compare(that.nav, nav) == 0 && Objects.equals(name, that.name) && Objects.equals(risk, that.risk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, risk, minSIP, expenseRatio, nav);
    }
}
