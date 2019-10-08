package com.alvis.exam.viewmodel.student.exam;

import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.student.exam.ExamPaperSubmitVM;
import lombok.Data;

@Data
public class ExamPaperReadVM {
    private ExamPaperEditRequestVM paper;
    private ExamPaperSubmitVM answer;
	public ExamPaperEditRequestVM getPaper() {
		return paper;
	}
	public void setPaper(ExamPaperEditRequestVM paper) {
		this.paper = paper;
	}
	public ExamPaperSubmitVM getAnswer() {
		return answer;
	}
	public void setAnswer(ExamPaperSubmitVM answer) {
		this.answer = answer;
	}
}
