package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedMutualFundFilter {
    private List<String> risk;

    private List<String> sipAllowed;

    private RangeValues fundSizeRange;
}
