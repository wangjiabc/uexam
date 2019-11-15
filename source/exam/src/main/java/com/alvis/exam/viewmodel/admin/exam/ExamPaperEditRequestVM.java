package com.alvis.exam.viewmodel.admin.exam;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class ExamPaperEditRequestVM {
    private Integer id;
    @NotNull
    private Integer level;
    @NotNull
    private Integer subjectId;
    @NotNull
    private Integer paperType;
    @NotBlank
    private String name;
    @NotNull
    private Integer suggestTime;

    private List<String> limitDateTime;

    private String score;

    private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Size(min = 1,message = "请添加试卷标题")
    @Valid
    private List<ExamPaperTitleItemVM> titleItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getPaperType() {
		return paperType;
	}

	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSuggestTime() {
		return suggestTime;
	}

	public void setSuggestTime(Integer suggestTime) {
		this.suggestTime = suggestTime;
	}

	public List<String> getLimitDateTime() {
		return limitDateTime;
	}

	public void setLimitDateTime(List<String> limitDateTime) {
		this.limitDateTime = limitDateTime;
	}

	public List<ExamPaperTitleItemVM> getTitleItems() {
		return titleItems;
	}

	public void setTitleItems(List<ExamPaperTitleItemVM> titleItems) {
		this.titleItems = titleItems;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}



}
