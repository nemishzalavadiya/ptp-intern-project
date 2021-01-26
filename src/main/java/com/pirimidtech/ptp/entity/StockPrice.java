package com.pirimidtech.ptp.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stockPrice")
public class StockPrice {

    @Id
    @Column
    public UUID stockPirceId;

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
