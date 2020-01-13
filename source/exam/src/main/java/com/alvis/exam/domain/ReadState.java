package com.alvis.exam.domain;

import java.io.Serializable;
import java.util.Date;

public class ReadState implements Serializable {
    private Integer id;

    private Integer articleId;

    private Integer readTime;

    private Integer count;

    private Integer userId;

    private Date startTime;

    private String readState;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getReadTime() {
        return readTime;
    }

    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String state) {
        this.readState = state == null ? null : state.trim();
    }
}