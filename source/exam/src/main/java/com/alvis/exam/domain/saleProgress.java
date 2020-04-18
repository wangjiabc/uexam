package com.alvis.exam.domain;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "brank")
public class saleProgress extends MessageRequestVM {
    @Column(name = "id")
    private Integer id;

    @Column(name = "manager")
    private String manager;

    @Column(name = "plan")
    private String plan;

    @Column(name = "complete")
    private String complete;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "ranking")
    private String ranking;

    @Column(name = "answerSales")
    private String answerSales;

    @Column(name = "answerProgress")
    private String answerProgress;

    @Column(name = "differentialSuper")
    private String differentialSuper;

    @Column(name = "differentialProgress")
    private String differentialProgress;

    @Column(name = "month")
    private String month;

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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getAnswerSales() {
        return answerSales;
    }

    public void setAnswerSales(String answerSales) {
        this.answerSales = answerSales;
    }

    public String getAnswerProgress() {
        return answerProgress;
    }

    public void setAnswerProgress(String answerProgress) {
        this.answerProgress = answerProgress;
    }

    public String getDifferentialSuper() {
        return differentialSuper;
    }

    public void setDifferentialSuper(String differentialSuper) {
        this.differentialSuper = differentialSuper;
    }

    public String getDifferentialProgress() {
        return differentialProgress;
    }

    public void setDifferentialProgress(String differentialProgress) {
        this.differentialProgress = differentialProgress;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
