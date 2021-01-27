package com.pirimidtech.ptp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mutualFundStatistic")
public class MutualFundStatistic {
    @Id
    private UUID mutualFundId;


    private String risk;
    private float minSip;
    private boolean sipAllowed;
    private float expenseRatio;
    private float nav;
    private Date fundStarted;
    private float fundSize;

    @OneToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name = "mutualFundId")
    private MutualFundDetail mutualFundDetail;


}
