package com.alvis.exam.viewmodel.admin.question;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionEditItemVM {
	@NotBlank
    private String prefix;
	@NotBlank
    private String content;

    private String score;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
