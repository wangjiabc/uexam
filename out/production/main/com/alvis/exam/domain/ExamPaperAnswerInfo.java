package com.alvis.exam.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperAnswerInfo {
    public ExamPaper examPaper;
    public ExamPaperAnswer examPaperAnswer;
    public List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers;
	public void setExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		this.examPaper=examPaper;
	}
	public void setExamPaperAnswer(ExamPaperAnswer examPaperAnswer) {
		this.examPaperAnswer = examPaperAnswer;
	}
	public void setExamPaperQuestionCustomerAnswers(
			List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers) {
		this.examPaperQuestionCustomerAnswers = examPaperQuestionCustomerAnswers;
	}
	public ExamPaper getExamPaper() {
		// TODO Auto-generated method stub
		return examPaper;
	}
	public ExamPaperAnswer getExamPaperAnswer() {
		// TODO Auto-generated method stub
		return examPaperAnswer;
	}
	public List<ExamPaperQuestionCustomerAnswer> getExamPaperQuestionCustomerAnswers() {
		// TODO Auto-generated method stub
		return examPaperQuestionCustomerAnswers;
	}
}
