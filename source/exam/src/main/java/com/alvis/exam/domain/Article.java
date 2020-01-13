package com.alvis.exam.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Article implements Serializable {
    private Integer id;

    private String title;

    private String author;

    private Date uploadDay;

    private Integer userId;

    private Integer typeId;

    private Integer maxIntegral;

    private Integer readTimeL;

    private Integer state;

    private Integer chapterId;

    private String content;

    private String plainText;

    private List<Integer> typeName;

    private String articleTypeName;

    private String chapterName;

    private Integer examPaperId;


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

    public Integer getMaxIntegral() {
        return maxIntegral;
    }

    public void setMaxIntegral(Integer maxIntegral) {
        this.maxIntegral = maxIntegral;
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

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}