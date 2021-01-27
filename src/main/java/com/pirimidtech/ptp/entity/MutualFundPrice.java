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
@Table(name = "MutualFundPrice")
public class MutualFundPrice {

    @Id
    @Column
    private UUID mutualFundPirceId;

    @Column
    private Integer  price;

    @Column
    private Date timestamp;


    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn( name = "mutualFundId")
    private MutualFundDetail mutualFundDetail;


}
