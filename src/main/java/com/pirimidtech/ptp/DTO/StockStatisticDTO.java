package com.pirimidtech.ptp.DTO;

import com.pirimidtech.ptp.entity.StockStatistic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockStatisticDTO {
    StockStatistic stockStatistic;
    boolean inWatchList;
}
