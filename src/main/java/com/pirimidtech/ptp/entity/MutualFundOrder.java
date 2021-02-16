package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundOrder")
public class MutualFundOrder {
    @Id
    @GeneratedValue
    private UUID id;

    private Date SIPDate;

    private float price;
    @Column(nullable = false)
    private float price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentType investmentType;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(nullable = false)
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;
}
