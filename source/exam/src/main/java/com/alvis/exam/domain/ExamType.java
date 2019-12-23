package com.alvis.exam.domain;

/**
 * 试卷类型
 */
public class ExamType {

    private static final long serialVersionUID = 0L;

    /**
     * 试卷类型ID
     */
    private Integer id;
    /**
     * 试卷类型
     */
    private String  paperType;

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
        this.paperType = paperType;
    }
}
