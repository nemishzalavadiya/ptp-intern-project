package com.pirimidtech.ptp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companyDetail")
public class companyDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyID")
    private UUID companyID;

    @Column(name = "name")
    private String name;

    @Column(name = "logoURL")
    private String logoURL;

    @Column(name = "assetClass")
    private String assetClass;

    @Column(name = "about")
    private String about;

    @Column(name = "managingDirector")
    private String managingDirector;

    @Column(name = "organization")
    private String organization;

    @OneToMany(mappedBy = "companyDetail", cascade = CascadeType.ALL)
    private List<stockDetail> stockDetailList;

    @OneToMany(mappedBy = "companyDetail")
    private List<stockBrands> stockBrandsList;

    public companyDetail(UUID companyID, String name, String logoURL, String assetClass, String about, String managingDirector, String organization) {
        this.companyID = companyID;
        this.name = name;
        this.logoURL = logoURL;
        this.assetClass = assetClass;
        this.about = about;
        this.managingDirector = managingDirector;
        this.organization = organization;
    }

    public UUID getCompanyID() {
        return companyID;
    }

    public void setCompanyID(UUID companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getManagingDirector() {
        return managingDirector;
    }

    public void setManagingDirector(String managingDirector) {
        this.managingDirector = managingDirector;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<stockDetail> getStockDetailList() {
        return stockDetailList;
    }

    public void setStockDetailList(List<stockDetail> stockDetailList) {
        this.stockDetailList = stockDetailList;
    }

    public List<stockBrands> getStockBrandsList() {
        return stockBrandsList;
    }

    public void setStockBrandsList(List<stockBrands> stockBrandsList) {
        this.stockBrandsList = stockBrandsList;
    }
}
