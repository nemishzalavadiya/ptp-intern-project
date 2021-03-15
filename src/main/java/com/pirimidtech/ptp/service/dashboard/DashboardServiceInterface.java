package com.pirimidtech.ptp.service.dashboard;

import com.pirimidtech.ptp.DTO.DashboardMutualFundDTO;
import com.pirimidtech.ptp.DTO.DashboardStockDTO;

import java.util.List;

public interface DashboardServiceInterface {

    List<DashboardStockDTO> getTopStocksByPeRatio();

    List<DashboardMutualFundDTO> getTopMutualFundsByRisk();
}
