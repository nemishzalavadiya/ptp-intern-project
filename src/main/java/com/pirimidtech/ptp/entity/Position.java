package com.pirimidtech.ptp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "position")

public class Position {

    @Id
    public Integer positionId;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "usedId")
    private User user;


    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyId")
    private CompanyDetail companyDetail;

    private Integer Volume;


    private float price;

    public Position(Integer positionId, User user, CompanyDetail companyDetail, Integer volume, float price) {
        this.positionId = positionId;
        this.user = user;
        this.companyDetail = companyDetail;
        Volume = volume;
        this.price = price;
    }
}
