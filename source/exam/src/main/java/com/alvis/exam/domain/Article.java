package com.alvis.exam.domain;

import com.alvis.exam.base.BasePage;

import java.io.Serializable;
import java.util.Date;

public class Article extends BasePage implements Serializable {
    private Integer id;

    private String title;

    private String author;

    private Date uploadDay;

    private Integer userId;

    private Integer typeId;

    private Integer readTimeU;

    private Integer readTimeL;

    private Integer state =1;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getUploadDay() {
        return uploadDay;
    }

    public void setUploadDay(Date uploadDay) {
        this.uploadDay = uploadDay;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getReadTimeU() {
        return readTimeU;
    }

    public void setReadTimeU(Integer readTimeU) {
        this.readTimeU = readTimeU;
    }

    public Integer getReadTimeL() {
        return readTimeL;
    }

    public void setReadTimeL(Integer readTimeL) {
        this.readTimeL = readTimeL;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}