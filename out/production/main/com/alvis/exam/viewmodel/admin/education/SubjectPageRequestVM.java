package com.alvis.exam.viewmodel.admin.education;

import com.alvis.exam.base.BasePage;
import lombok.Data;

@Data
public class SubjectPageRequestVM extends BasePage {
    private Integer id;
    private Integer level;
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
}
