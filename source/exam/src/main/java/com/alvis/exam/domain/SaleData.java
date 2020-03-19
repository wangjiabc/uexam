package com.alvis.exam.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "t_sale_data")
public class SaleData implements Serializable {
    @Column(name = "id")
    private Integer id;

    @Column(name = "users_id")
    private String usersId;

    @Column(name = "real_sale")
    private Integer realSale;

    @Column(name = "date")
    private Date date;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
}