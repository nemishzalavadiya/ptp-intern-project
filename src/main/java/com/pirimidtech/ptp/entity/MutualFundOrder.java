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
@Table(name ="mutualFundOrder")
public class MutualFundOrder {
    @Id
    private UUID id;

    private LocalDateTime SIPDate;

    private Float price;

    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    private User user;
}
