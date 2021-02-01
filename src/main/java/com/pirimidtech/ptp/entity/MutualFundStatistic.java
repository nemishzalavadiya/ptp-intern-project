package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    private UUID id;

    private String risk;

    private Float minSip;

    private Boolean SipAllowed;

    private Float expenseRatio;

    private Float nav;

    private LocalDateTime fundStarted;

    private Float fundSize;

    @OneToOne(targetEntity = MutualFundDetail.class)
    private MutualFundDetail mutualFundDetail;
}
