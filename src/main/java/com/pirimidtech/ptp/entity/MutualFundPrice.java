package com.pirimidtech.ptp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MutualFundPrice")
public class MutualFundPrice {

    @Id
    @Column
    public UUID MutualFundPirceId;

    @Column
    private Integer  price;

    @Column
    private Date timestamp;


    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn( name = "mutualFundId")
    private MutualFundDetail mutualFundDetail;

    public MutualFundPrice(UUID mutualFundPirceId, Integer price, Date timestamp, MutualFundDetail mutualFundDetail) {
        MutualFundPirceId = mutualFundPirceId;
        this.price = price;
        this.timestamp = timestamp;
        this.mutualFundDetail = mutualFundDetail;
    }
}
