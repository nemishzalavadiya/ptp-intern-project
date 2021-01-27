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
@Table(name = "mutualFundCategory")
public class MutualFundCategory {
    @Id
    private UUID mutualFundCategoryId;

    private String name;

    @ManyToMany
    @JoinTable(name = "MutualFundCategoryFromDetail",
            joinColumns = @JoinColumn(name = "mutualFundCategoryId"),
            inverseJoinColumns = @JoinColumn(name = "mutualFundId"))
    private List<MutualFundDetail> mutualFundDetails;


    private String fundManager;

}

