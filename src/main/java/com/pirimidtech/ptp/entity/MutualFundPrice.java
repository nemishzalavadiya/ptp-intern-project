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
@Table(name = "mutualFundPrice")
public class MutualFundPrice {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private float price;

    private Date timestamp;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    @JoinColumn(nullable = false)
    private MutualFundDetail mutualFundDetail;
}
