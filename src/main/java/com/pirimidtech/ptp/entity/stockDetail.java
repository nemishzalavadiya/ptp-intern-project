package com.pirimidtech.ptp.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "stockDetail")
public class stockDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockID")
    private UUID stockID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyID")
    private companyDetail companydetail;

    @Column(name = "yearFounded")
    private Date yearFounded;

    @Column(name = "managingDirector")
    private String managingDirector;

    @OneToOne(mappedBy = "stockDetail")
    private stockStatistic stockStatistic;

    public stockDetail(){

    }

    public stockDetail(UUID stockID, companyDetail companydetail, Date yearFounded, String managingDirector) {
        this.stockID = stockID;
        this.companydetail = companydetail;
        this.yearFounded = yearFounded;
        this.managingDirector = managingDirector;
    }

    public UUID getStockID() {
        return stockID;
    }

    public void setStockID(UUID stockID) {
        this.stockID = stockID;
    }

    public companyDetail getCompanydetail() {
        return companydetail;
    }

    public void setCompanydetail(companyDetail companydetail) {
        this.companydetail = companydetail;
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
}
