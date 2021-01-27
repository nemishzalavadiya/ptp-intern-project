package com.pirimidtech.ptp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockPrice")
public class StockPrice {

    @Id
    @Column
    private UUID stockPirceId;

    @Column
    private Integer  price;

    @Column
    private Date timestamp;

    @Column
    private char stockExchange;// BSE or NSE

    @ManyToOne(targetEntity = StockDetail.class)
    @JoinColumn( name = "stockId")
    private StockDetail stockDetail;


}
