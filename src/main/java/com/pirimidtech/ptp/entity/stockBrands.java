package com.pirimidtech.ptp.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class stockBrands {
    @Id
    @Column(name = "brandID")
    private UUID brandID;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "brandLogoURL")
    private String brandLogoURL;

    @ManyToOne
    @JoinColumn(name = "companyID")
    private companyDetail companyDetail;

    public stockBrands(UUID brandID, String brandName, String brandLogoURL) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandLogoURL = brandLogoURL;
    }
    public stockBrands(){

    }

    public UUID getBrandID() {
        return brandID;
    }

    public void setBrandID(UUID brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogoURL() {
        return brandLogoURL;
    }

    public void setBrandLogoURL(String brandLogoURL) {
        this.brandLogoURL = brandLogoURL;
    }

    public com.pirimidtech.ptp.entity.companyDetail getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(com.pirimidtech.ptp.entity.companyDetail companyDetail) {
        this.companyDetail = companyDetail;
    }
}
