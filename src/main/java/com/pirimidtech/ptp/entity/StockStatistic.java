package com.pirimidtech.ptp.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class StockStatistic {
    @Id
    @Column(name = "stockID")
    private UUID stockID;

    @Column(name = "numberOfStackHolders")
    private Integer numberOfStackHolders;

    @Column(name = "pbRatio")
    private float pbRatio;

    @Column(name = "peRatio")
    private float peRatio;

    @Column(name = "industryPE")
    private float industryPE;

    @Column(name = "divYield")
    private float divYield;

    @Column(name = "bookValue")
    private float bookValue;

    @Column(name = "marketCap")
    private float marketCap;

    @Column(name = "returnOnEquity")
    private float returnOnEquity; //ROE

    @Column(name = "earningPerShare")
    private float earningPerShareTTM; //EESTTM

    @OneToOne
    @PrimaryKeyJoinColumn(name = "stockID")
    private StockDetail stockDetail;

    public StockStatistic(UUID stockID, Integer numberOfStackHolders, float pbRatio, float peRatio, float industryPE, float divYield, float bookValue, float marketCap, float returnOnEquity, float earningPerShareTTM) {
        this.stockID = stockID;
        this.numberOfStackHolders = numberOfStackHolders;
        this.pbRatio = pbRatio;
        this.peRatio = peRatio;
        this.industryPE = industryPE;
        this.divYield = divYield;
        this.bookValue = bookValue;
        this.marketCap = marketCap;
        this.returnOnEquity = returnOnEquity;
        this.earningPerShareTTM = earningPerShareTTM;
    }

    public StockStatistic() {
    }

    public UUID getStockID() {
        return stockID;
    }

    public void setStockID(UUID stockID) {
        this.stockID = stockID;
    }

    public Integer getNumberOfStackHolders() {
        return numberOfStackHolders;
    }

    public void setNumberOfStackHolders(Integer numberOfStackHolders) {
        this.numberOfStackHolders = numberOfStackHolders;
    }

    public float getPbRatio() {
        return pbRatio;
    }

    public void setPbRatio(float pbRatio) {
        this.pbRatio = pbRatio;
    }

    public float getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(float peRatio) {
        this.peRatio = peRatio;
    }

    public float getIndustryPE() {
        return industryPE;
    }

    public void setIndustryPE(float industryPE) {
        this.industryPE = industryPE;
    }

    public float getDivYield() {
        return divYield;
    }

    public void setDivYield(float divYield) {
        this.divYield = divYield;
    }

    public float getBookValue() {
        return bookValue;
    }

    public void setBookValue(float bookValue) {
        this.bookValue = bookValue;
    }

    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }

    public float getReturnOnEquity() {
        return returnOnEquity;
    }

    public void setReturnOnEquity(float returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
    }

    public float getEarningPerShareTTM() {
        return earningPerShareTTM;
    }

    public void setEarningPerShareTTM(float earningPerShareTTM) {
        this.earningPerShareTTM = earningPerShareTTM;
    }

    public StockDetail getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(StockDetail stockDetail) {
        this.stockDetail = stockDetail;
    }
}
