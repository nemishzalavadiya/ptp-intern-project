package com.pirimidtech.ptp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DashboardMutualFundDTO {
    private UUID assetDetailId;
    private String name;
    private String iconUrl;
    private String risk;
}
