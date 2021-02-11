package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
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

    private LocalDate SIPDate;

    private float price;

    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    private User user;
}
