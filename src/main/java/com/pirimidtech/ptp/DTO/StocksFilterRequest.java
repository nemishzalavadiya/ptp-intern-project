package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StocksFilterRequest {
    private Float closingPriceUpperLimit;

    private Float closingPriceLowerLimit;

    private Float marketCapUpperLimit;

    private Float marketCapLowerLimit;
}
