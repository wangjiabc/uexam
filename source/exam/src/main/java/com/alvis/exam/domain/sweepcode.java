package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sweepcode")
public class sweepcode extends MessageRequestVM {
    @Column(name = "id")
    private Integer id;

    @Column(name = "area")
    private String area;

    @Column(name = "manager")
    private String manager;

    @Column(name = "answerNumber")
    private String answerNumber;

    @Column(name = "actualNumber")
    private String actualNumber;

    @Column(name = "notNumber")
    private String notNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(String answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(String actualNumber) {
        this.actualNumber = actualNumber;
    }

    public String getNotNumber() {
        return notNumber;
    }

    public void setNotNumber(String notNumber) {
        this.notNumber = notNumber;
    }

}
