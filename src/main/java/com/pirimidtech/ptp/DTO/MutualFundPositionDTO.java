package com.pirimidtech.ptp.DTO;

import com.pirimidtech.ptp.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundPositionDTO {
    private String assetName;
    private UUID assetId;
    private float quantity; //volume
    private float totalAmount; //price
    private float nav;
    private float netValue; //current Value
    private float profitPercentage;
    private float avgNav;
    private float profit;

}