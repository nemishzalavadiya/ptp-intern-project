package com.pirimidtech.ptp.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
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
    private UUID stockID;
    private Date yearFounded;
    private String managingDirector;
    @ManyToOne(targetEntity = CompanyDetail.class)
    @JoinColumn(name = "companyID")
    private CompanyDetail companyDetail;

    //@OneToOne(mappedBy = "StockDetail", cascade = CascadeType.ALL)
    @OneToOne(mappedBy ="stockDetail")
    @PrimaryKeyJoinColumn
    private StockStatistic stockStatistic;

    @OneToMany(mappedBy = "stockDetail")
    private List<StockBrands> stockBrandsList;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockPrice> stockPriceList;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockOrder> stockOrderList;

    @OneToMany(mappedBy = "stockDetail",cascade = CascadeType.ALL)
    private List<StockTradeHistory> stockTradeHistoryList;


}