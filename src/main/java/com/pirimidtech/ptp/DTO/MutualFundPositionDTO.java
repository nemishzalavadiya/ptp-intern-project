package com.pirimidtech.ptp.DTO;

import com.pirimidtech.ptp.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundPositionDTO {
    private Position position;
    private float nav;
    private float netValue;
    private float profit;

}