package com.alvis.exam.domain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "source_put")
public class SourcePut {
    @Column(name = "id")
    private Integer id;

    @Column(name = "customerNumber")
    private String customerNumber;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerGrade")
    private String customerGrade;

    @Column(name = "orderCycle")
    private String orderCycle;

    @Column(name = "orderPhone")
    private String orderPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "marketingArea")
    private String marketingArea;

    @Column(name = "accountManager")
    private String accountManager;

    @Column(name = "putNumber")
    private Integer putNumber;

    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Column(name = "needNumber")
    private Integer needNumber;

    @Column(name = "utilizationRate")
    private String utilizationRate;

    @Column(name = "satisfactionRate")
    private String satisfactionRate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    public String getOrderCycle() {
        return orderCycle;
    }

    public void setOrderCycle(String orderCycle) {
        this.orderCycle = orderCycle;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getMarketingArea() {
        return marketingArea;
    }

    public void setMarketingArea(String marketingArea) {
        this.marketingArea = marketingArea;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public Integer getPutNumber() {
        return putNumber;
    }

    public void setPutNumber(Integer putNumber) {
        this.putNumber = putNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(Integer needNumber) {
        this.needNumber = needNumber;
    }

    public String getUtilizationRate() {
        return utilizationRate;
    }

    public void setUtilizationRate(String utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    public String getSatisfactionRate() {
        return satisfactionRate;
    }

    public void setSatisfactionRate(String satisfactionRate) {
        this.satisfactionRate = satisfactionRate;
    }




}