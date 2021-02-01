package com.pirimidtech.ptp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundWatchlistDTO {
    private String name;

    private String risk;

    private Float minSIP;

    private Float expenseRatio;

    private Float nav;
}
