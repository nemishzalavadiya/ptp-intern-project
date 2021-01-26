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

    @ManyToMany
    private List<MutualFundDetail> mutualFundDetail;
}

