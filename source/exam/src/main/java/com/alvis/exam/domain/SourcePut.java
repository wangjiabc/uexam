package com.alvis.exam.domain;

import java.io.Serializable;

public class SourcePut {
    private Integer id;

    private String customernumber;

    private String customername;

    private String customergrade;

    private String ordercycle;

    private String orderphone;

    private String address;

    private String responsible;

    private String marketingarea;

    private String accountmanager;

    private Integer putnumber;

    private Integer ordernumber;

    private Integer neednumber;

    private String utilizationrate;

    private String satisfactionrate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber == null ? null : customernumber.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getCustomergrade() {
        return customergrade;
    }

    public void setCustomergrade(String customergrade) {
        this.customergrade = customergrade == null ? null : customergrade.trim();
    }

    public String getOrdercycle() {
        return ordercycle;
    }

    public void setOrdercycle(String ordercycle) {
        this.ordercycle = ordercycle == null ? null : ordercycle.trim();
    }

    public String getOrderphone() {
        return orderphone;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone == null ? null : orderphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible == null ? null : responsible.trim();
    }

    public String getMarketingarea() {
        return marketingarea;
    }

    public void setMarketingarea(String marketingarea) {
        this.marketingarea = marketingarea == null ? null : marketingarea.trim();
    }

    public String getAccountmanager() {
        return accountmanager;
    }

    public void setAccountmanager(String accountmanager) {
        this.accountmanager = accountmanager == null ? null : accountmanager.trim();
    }

    public Integer getPutnumber() {
        return putnumber;
    }

    public void setPutnumber(Integer putnumber) {
        this.putnumber = putnumber;
    }

    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getNeednumber() {
        return neednumber;
    }

    public void setNeednumber(Integer neednumber) {
        this.neednumber = neednumber;
    }

    public String getUtilizationrate() {
        return utilizationrate;
    }

    public void setUtilizationrate(String utilizationrate) {
        this.utilizationrate = utilizationrate == null ? null : utilizationrate.trim();
    }

    public String getSatisfactionrate() {
        return satisfactionrate;
    }

    public void setSatisfactionrate(String satisfactionrate) {
        this.satisfactionrate = satisfactionrate == null ? null : satisfactionrate.trim();
    }
}