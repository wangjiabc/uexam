package com.alvis.exam.viewmodel.student.dashboard;

import lombok.Data;

import java.util.Date;

@Data
public class PaperInfoVM extends PaperInfo {
    private String startTime;
    private String endTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
