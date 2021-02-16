package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
