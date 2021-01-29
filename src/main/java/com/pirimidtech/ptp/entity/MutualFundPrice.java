package com.pirimidtech.ptp.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mutualFundPrice")
public class MutualFundPrice {
    @Id
    private UUID id;

    private Integer price;

    private LocalDateTime timestamp;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    private MutualFundDetail mutualFundDetail;
}
