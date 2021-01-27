package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="mutualFundOrder")
public class MutualFundOrder {

    @Id
    private UUID transactionId;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name  = "MutualFundId")
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    private Date SIPDate;

    private float price;

    private char isOneTime;

}
