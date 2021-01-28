package com.pirimidtech.ptp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")

public class Position {

    @Id
    private UUID positionId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "usedId")
    private User user;

    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyId")
    private CompanyDetail companyDetail;

    private Integer Volume;

    private float price;
    
}
