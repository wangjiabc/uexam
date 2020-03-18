package com.alvis.exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperTitleItemObject {

    private String name;

    private List<ExamPaperQuestionItemObject> questionItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExamPaperQuestionItemObject> getQuestionItems() {
		return questionItems;
	}

	public void setQuestionItems(List<ExamPaperQuestionItemObject> questionItems) {
		this.questionItems = questionItems;
	}
}
