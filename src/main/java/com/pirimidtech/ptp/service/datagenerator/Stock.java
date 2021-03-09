package com.pirimidtech.ptp.service.datagenerator;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Stock {
    private Date timestamp;
    private UUID companyId;
    private String companyName;
    private float open;
    private float close;
    private float marketPrice;
    private float high;
    private float low;
    private float percentageChange;
}
