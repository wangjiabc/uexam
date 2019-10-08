package com.alvis.exam.viewmodel.student.dashboard;

import com.alvis.exam.domain.task.TaskItemObject;
import lombok.Data;

import java.util.List;

@Data
public class TaskItemVm {
    private Integer id;
    private String title;
    private List<TaskItemPaperVm> paperItems;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<TaskItemPaperVm> getPaperItems() {
		return paperItems;
	}
	public void setPaperItems(List<TaskItemPaperVm> paperItems) {
		this.paperItems = paperItems;
	}
}
