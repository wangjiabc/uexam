package com.alvis.exam.domain;

import java.io.Serializable;

public class Chapter implements Serializable {
    private Integer id;

    private String name;

    private Integer typeId;

    private Integer sequence;

    private Integer criterion;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getCriterion() {
        return criterion;
    }

    public void setCriterion(Integer criterion) {
        this.criterion = criterion;
    }
}