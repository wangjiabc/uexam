package com.alvis.exam.viewmodel.student.exam;

import com.alvis.exam.base.BasePage;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExamPaperPageVM extends BasePage {
	@NotNull
    private Integer paperType;
    private Integer subjectId;
    private Integer levelId;
    private Integer userId;
    private Integer typeId;
    private Integer chapterId;
	public Integer getPaperType() {
		return paperType;
	}
	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
}
