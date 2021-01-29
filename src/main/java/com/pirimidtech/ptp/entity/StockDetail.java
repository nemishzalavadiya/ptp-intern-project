package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockDetail")
public class StockDetail {
    @Id
    private UUID id;

    private LocalDateTime yearFounded;

    private String managingDirector;

    @OneToOne(mappedBy = "stockDetail")
    private CompanyDetail companyDetail;

    @OneToOne(mappedBy = "stockDetail")
    @PrimaryKeyJoinColumn
    private StockStatistic stockStatistic;

    @OneToMany(mappedBy = "stockDetail", cascade = CascadeType.ALL)
    private List<StockPrice> stockPriceList;

    @OneToMany(mappedBy = "stockDetail", cascade = CascadeType.ALL)
    private List<StockOrder> stockOrderList;

    @OneToMany(mappedBy = "stockDetail", cascade = CascadeType.ALL)
    private List<StockTradeHistory> stockTradeHistoryList;
}