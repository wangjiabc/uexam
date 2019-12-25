package com.alvis.exam.domain;

import java.io.Serializable;

public class ExamType implements Serializable {
    private Integer id;

    private String paperType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType == null ? null : paperType.trim();
    }
}