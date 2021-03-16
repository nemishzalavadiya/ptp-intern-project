package com.pirimidtech.ptp.service.datagenerator;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class MutualFund {
    private Date timestamp;
    private UUID companyId;
    private String companyName;
    private String risk;
    private float expenseRatio;
    private float nav;
    private float fundSize;
}

