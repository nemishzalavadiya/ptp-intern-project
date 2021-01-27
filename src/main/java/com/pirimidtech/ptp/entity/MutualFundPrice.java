package com.pirimidtech.ptp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundPrice")
public class MutualFundPrice {

    @Id
    private UUID mutualFundPirceId;

    private Integer  price;

    private Date timestamp;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn( name = "mutualFundId")
    private MutualFundDetail mutualFundDetail;
}
