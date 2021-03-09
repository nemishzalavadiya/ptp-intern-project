package com.pirimidtech.ptp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DashboardDTO {
    private List<DashboardStockDTO> dashboardStockDTOList;
    private List<DashboardMutualFundDTO> dashboardMutualFundDTOList;
}
