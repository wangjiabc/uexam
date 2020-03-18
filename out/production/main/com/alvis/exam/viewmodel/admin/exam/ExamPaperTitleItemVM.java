package com.alvis.exam.viewmodel.admin.exam;

import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExamPaperTitleItemVM {

    @NotBlank(message = "标题内容不能为空")
    private String name;

    @Size(min = 1,message = "请添加题目")
    @Valid
    private List<QuestionEditRequestVM> questionItems;

	public void setQuestionItems(List<QuestionEditRequestVM> questionItemsVM) {
		// TODO Auto-generated method stub
		this.questionItems=questionItemsVM;
	}

	public @Size(min = 1, message = "请添加题目") @Valid List<QuestionEditRequestVM> getQuestionItems() {
		// TODO Auto-generated method stub
		return questionItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
