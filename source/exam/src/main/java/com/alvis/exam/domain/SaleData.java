package com.alvis.exam.domain;

import java.io.Serializable;
import java.util.Date;

public class SaleData implements Serializable {
    private Integer id;

    private Integer usersId;

    private Integer realSale;

    private Date date;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getRealSale() {
        return realSale;
    }

    public void setRealSale(Integer realSale) {
        this.realSale = realSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}