package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFundFilterRequest {
    private ArrayList<String> risk;

    private Boolean sipAllowed;

    private Float openSize;

    private Float closeSize;
}
