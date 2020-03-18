package com.alvis.exam.viewmodel.admin.task;

import com.alvis.exam.base.BasePage;
import lombok.Data;

@Data
public class TaskPageRequestVM extends BasePage {
    private Integer gradeLevel;

	public Integer getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(Integer gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
}
