package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundStatistic")
public class MutualFundStatistic {
    @Id
    private UUID MutualFundID;

    private String risk;

    private Float minSIP;

    private Boolean SIPAllowed;

    private Float expenseRatio;

    private Float NAV;

    private LocalDateTime fundStarted;

    private Float fundSize;

    @OneToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name = "mutualFundID")
    private MutualFundDetail mutualFundDetail;
}
