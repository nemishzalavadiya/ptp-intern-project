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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID mutualFundCategoryID;

    private String name;

//    @ManyToMany(targetEntity = MutualFundDetail.class, cascade = CascadeType.ALL, mappedBy = "mutualFundCategory")
//    @JoinColumn(name = "mutualFundID")

    @ManyToMany
    @JoinTable(name = "MutualFundCategoryFromDetail",
            joinColumns = @JoinColumn(name = "mutualFundCategoryID"),
            inverseJoinColumns = @JoinColumn(name = "mutualFundID"))
    private List<MutualFundDetail> mutualFundDetail;
}

