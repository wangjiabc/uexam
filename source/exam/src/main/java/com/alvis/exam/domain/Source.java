package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "source")
public class Source extends MessageRequestVM {
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private String level;

    @Column(name = "orderingCycle")
    private String orderingCycle;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "area")
    private String area;

    @Column(name = "manager")
    private String manager;

    @Column(name = "putNumber")
    private Integer putNumber;

    @Column(name = "OrderNumber")
    private Integer OrderNumber;

    @Column(name = "needNumber")
    private Integer needNumber;

    @Column(name = "useRate")
    private String useRate;

    @Column(name = "satisfyRate")
    private String satisfyRate;

    @Column(name = "type")
    private String type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrderingCycle() {
        return orderingCycle;
    }

    public void setOrderingCycle(String orderingCycle) {
        this.orderingCycle = orderingCycle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getPutNumber() {
        return putNumber;
    }

    public void setPutNumber(Integer putNumber) {
        this.putNumber = putNumber;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        OrderNumber = orderNumber;
    }

    public Integer getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(Integer needNumber) {
        this.needNumber = needNumber;
    }

    public String getUseRate() {
        return useRate;
    }

    public void setUseRate(String useRate) {
        this.useRate = useRate;
    }

    public String getSatisfyRate() {
        return satisfyRate;
    }

    public void setSatisfyRate(String satisfyRate) {
        this.satisfyRate = satisfyRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
