package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MutualFundPrice")
public class MutualFundPrice {

    @Id
    private UUID mutualFundPirceId;

    private Integer price;

    private Date timestamp;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(name = "mutualFundId")
    private MutualFundDetail mutualFundDetail;


}
