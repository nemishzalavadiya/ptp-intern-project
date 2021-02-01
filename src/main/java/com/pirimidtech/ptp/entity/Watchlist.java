package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchList")
public class Watchlist {
    @Id
    private UUID id;

    @OneToOne
    private User user;

    @OneToOne
    private CompanyDetail companyDetail;
}
