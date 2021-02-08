package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    private float price;

    private LocalDateTime timestamp;

    @ManyToOne(targetEntity = MutualFundDetail.class)
    private MutualFundDetail mutualFundDetail;
}
