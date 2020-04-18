package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "cashregister")
public class cashregister extends MessageRequestVM{
    @Column(name = "id")
    private Integer id;

    @Column(name = "shoopName")
    private String shoopName;

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "saleNumber")
    private Integer saleNumber;

    @Column(name = "stripSmokeNumber")
    private Integer stripSmokeNumber;

    @Column(name = "wrapSmokeNumber")
    private Integer wrapSmokeNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShoopName() {
        return shoopName;
    }

    public void setShoopName(String shoopName) {
        this.shoopName = shoopName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Integer getStripSmokeNumber() {
        return stripSmokeNumber;
    }

    public void setStripSmokeNumber(Integer stripSmokeNumber) {
        this.stripSmokeNumber = stripSmokeNumber;
    }

    public Integer getWrapSmokeNumber() {
        return wrapSmokeNumber;
    }

    public void setWrapSmokeNumber(Integer wrapSmokeNumber) {
        this.wrapSmokeNumber = wrapSmokeNumber;
    }

}
