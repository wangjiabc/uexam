package com.alvis.exam.domain.task;

import lombok.Data;

@Data
public class TaskItemAnswerObject {
    private Integer examPaperId;
    private Integer examPaperAnswerId;
    private Integer status;

    public TaskItemAnswerObject(){

    }

    public TaskItemAnswerObject(Integer examPaperId, Integer examPaperAnswerId, Integer status) {
        this.setExamPaperId(examPaperId);
        this.setExamPaperAnswerId(examPaperAnswerId);
        this.setStatus(status);
    }

	public Integer getExamPaperId() {
		return examPaperId;
	}

	public void setExamPaperId(Integer examPaperId) {
		this.examPaperId = examPaperId;
	}

	public Integer getExamPaperAnswerId() {
		return examPaperAnswerId;
	}

	public void setExamPaperAnswerId(Integer examPaperAnswerId) {
		this.examPaperAnswerId = examPaperAnswerId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
