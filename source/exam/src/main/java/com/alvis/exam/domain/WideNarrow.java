package com.alvis.exam.domain;

/**
 * 宽窄数据
 */
public class WideNarrow {

    private static final long serialVersionUID = 0L;

    private String accountManager;
    private float needNumber;
    private float orderNumber;
    private double orderMoney;
    private float orderRate;
    private float accountGross;
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
