package com.alvis.exam.viewmodel.student.question.answer;

import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperSubmitItemVM;
import lombok.Data;

@Data
public class QuestionAnswerVM {
    private QuestionEditRequestVM questionVM;
    private ExamPaperSubmitItemVM questionAnswerVM;
	public QuestionEditRequestVM getQuestionVM() {
		return questionVM;
	}
	public void setQuestionVM(QuestionEditRequestVM questionVM) {
		this.questionVM = questionVM;
	}
	public ExamPaperSubmitItemVM getQuestionAnswerVM() {
		return questionAnswerVM;
	}
	public void setQuestionAnswerVM(ExamPaperSubmitItemVM questionAnswerVM) {
		this.questionAnswerVM = questionAnswerVM;
	}
}
