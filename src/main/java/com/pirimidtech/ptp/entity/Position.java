package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    private UUID id;

    private int volume;

    private float price;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = AssetDetail.class)
    private AssetDetail assetDetail;
}
