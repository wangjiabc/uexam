package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "brank")
public class brank extends MessageRequestVM {
    @Column(name = "id")
    private Integer id;

    @Column(name = "manager")
    private String manager;

    @Column(name = "plan")
    private String plan;

    @Column(name = "complete")
    private String complete;

    @Column(name = "completionRate")
    private String completionRate;

    @Column(name = "type")
    private String type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
