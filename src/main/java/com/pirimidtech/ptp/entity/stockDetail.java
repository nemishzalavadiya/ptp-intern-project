package com.pirimidtech.ptp.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stockDetail")
public class stockDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockID")
    private UUID stockID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stockID")
    private stockDetail stockDetail;

    @Column(name = "yearFounded")
    private Date yearFounded;

    @Column(name = "managingDirector")
    private String managingDirector;

    @OneToOne(mappedBy = "stockDetail")
    private stockStatistic stockStatistic;

    @OneToMany(mappedBy = "stockDetail")
    private List<stockBrands> stockBrandsList;

    public stockDetail(){

    }

    public stockDetail(UUID stockID, stockDetail stockDetail, Date yearFounded, String managingDirector) {
        this.stockID = stockID;
        this.stockDetail = stockDetail;
        this.yearFounded = yearFounded;
        this.managingDirector = managingDirector;
    }

    public UUID getStockID() {
        return stockID;
    }

    public void setStockID(UUID stockID) {
        this.stockID = stockID;
    }

    public stockDetail getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(stockDetail stockdetail) {
        this.stockDetail = stockdetail;
    }

    public Date getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Date yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getManagingDirector() {
        return managingDirector;
    }

    public void setManagingDirector(String managingDirector) {
        this.managingDirector = managingDirector;
    }

    public List<stockBrands> getStockBrandsList() {
        return stockBrandsList;
    }

    public void setStockBrandsList(List<stockBrands> stockBrandsList) {
        this.stockBrandsList = stockBrandsList;
    }
}
