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
@Table(name ="mutualFundOrder")
public class MutualFundOrder {

    @Id
    public UUID transactionId;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name  = "MutualFundId")
    private MutualFundDetail mutualFundDetail;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    private Date SIPDate;

    private float price;

    private char isOneTime;

    public MutualFundOrder(UUID transactionId, MutualFundDetail mutualFundDetail, User user, Date SIPDate, float price, char isOneTime) {
        this.transactionId = transactionId;
        this.mutualFundDetail = mutualFundDetail;
        this.user = user;
        this.SIPDate = SIPDate;
        this.price = price;
        this.isOneTime = isOneTime;
    }
}
