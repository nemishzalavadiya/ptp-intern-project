package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.pirimidtech.ptp.entity.Position;

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