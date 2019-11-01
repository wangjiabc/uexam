package com.alvis.exam.domain;

import java.io.Serializable;

public class ArticleType implements Serializable {
    private Integer id;

    private String typeName;

    private String pathDeposit;

    private String origname;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getPathDeposit() {
        return pathDeposit;
    }

    public void setPathDeposit(String pathDeposit) {
        this.pathDeposit = pathDeposit == null ? null : pathDeposit.trim();
    }

    public String getOrigname() {
        return origname;
    }

    public void setOrigname(String origname) {
        this.origname = origname == null ? null : origname.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}