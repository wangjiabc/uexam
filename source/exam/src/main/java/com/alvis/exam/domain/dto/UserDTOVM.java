package com.alvis.exam.domain.dto;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDTOVM extends MessageRequestVM {
	/**
	 * 开始时间
	 */

	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
