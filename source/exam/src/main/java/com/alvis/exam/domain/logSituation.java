package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "brank")
public class logSituation extends MessageRequestVM {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Integer id;

    @Column(name = "monopolyCard")
    private String monopolyCard;

    @Column(name = "phone")
    private String phone;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "status")
    private String status;

    @Column(name = "phoneOrder")
    private String phoneOrder;

    @Column(name = "customerManager")
    private String customerManager;

    @Column(name = "customerStatus")
    private String customerStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonopolyCard() {
        return monopolyCard;
    }

    public void setMonopolyCard(String monopolyCard) {
        this.monopolyCard = monopolyCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneOrder() {
        return phoneOrder;
    }

    public void setPhoneOrder(String phoneOrder) {
        this.phoneOrder = phoneOrder;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

}
