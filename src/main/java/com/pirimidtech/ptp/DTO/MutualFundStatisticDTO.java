package com.pirimidtech.ptp.DTO;

import com.pirimidtech.ptp.entity.MutualFundStatistic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundStatisticDTO {
    MutualFundStatistic mutualFundStatistic;
    boolean inWatchList;
}
