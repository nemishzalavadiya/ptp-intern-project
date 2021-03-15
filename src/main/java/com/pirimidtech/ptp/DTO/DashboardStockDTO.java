package com.pirimidtech.ptp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DashboardStockDTO {
    private UUID assetDetailId;
    private String name;
    private String iconUrl;
    private float peRatio;
}
