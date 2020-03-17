package com.alvis.exam.viewmodel.admin.user;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

/**
 * 思维导图
 */
public class SourcePutVM extends MessageRequestVM {

    private static final long serialVersionUID = 0L;

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
        this.customernumber = customernumber;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomergrade() {
        return customergrade;
    }

    public void setCustomergrade(String customergrade) {
        this.customergrade = customergrade;
    }

    public String getOrdercycle() {
        return ordercycle;
    }

    public void setOrdercycle(String ordercycle) {
        this.ordercycle = ordercycle;
    }

    public String getOrderphone() {
        return orderphone;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone;
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

    public String getMarketingarea() {
        return marketingarea;
    }

    public void setMarketingarea(String marketingarea) {
        this.marketingarea = marketingarea;
    }

    public String getAccountmanager() {
        return accountmanager;
    }

    public void setAccountmanager(String accountmanager) {
        this.accountmanager = accountmanager;
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
        this.utilizationrate = utilizationrate;
    }

    public String getSatisfactionrate() {
        return satisfactionrate;
    }

    public void setSatisfactionrate(String satisfactionrate) {
        this.satisfactionrate = satisfactionrate;
    }
}
