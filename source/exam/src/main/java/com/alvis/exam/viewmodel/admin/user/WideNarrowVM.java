package com.alvis.exam.viewmodel.admin.user;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;

/**
 * 宽窄
 */
public class WideNarrowVM extends MessageRequestVM {

    private static final long serialVersionUID = 0L;
    @Column(name = "accountManager")
    private String accountManager;

    @Column(name = "needNumber")
    private float needNumber;

    @Column(name = "orderNumber")
    private float orderNumber;

    @Column(name = "orderMoney")
    private double orderMoney;

    @Column(name = "orderRate")
    private float orderRate;

    @Column(name = "accountGross")
    private float accountGross;

    @Column(name = "orderTotal")
    private Integer orderTotal;

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public float getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(float needNumber) {
        this.needNumber = needNumber;
    }

    public float getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(float orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public float getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(float orderRate) {
        this.orderRate = orderRate;
    }

    public float getAccountGross() {
        return accountGross;
    }

    public void setAccountGross(float accountGross) {
        this.accountGross = accountGross;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }
}
