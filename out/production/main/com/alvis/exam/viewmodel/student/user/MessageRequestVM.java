package com.alvis.exam.viewmodel.student.user;

import com.alvis.exam.base.BasePage;
import lombok.Data;

@Data
public class MessageRequestVM extends BasePage {

    private Integer receiveUserId;

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}


}
