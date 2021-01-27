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
@Table(name ="mutualFundOrder")
public class MutualFundOrder {
    public enum InvestmentType{
        MONTHLY_SIP,
        ONE_TIME
    }
    @Id
    private UUID mutualFundOrderId;

    private LocalDateTime SIPDate;

    private Float price;

    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name  = "MutualFundId")
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
}
