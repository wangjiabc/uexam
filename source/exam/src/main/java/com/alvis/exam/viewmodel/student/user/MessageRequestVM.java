package com.alvis.exam.viewmodel.student.user;

import com.alvis.exam.base.BasePage;
import lombok.Data;

import java.util.Date;

@Data
public class MessageRequestVM extends BasePage {

    private Integer receiveUserId;

    private Date startDate;

    private Date endDate;

    private String khjl;

	public String getKhjl() {
		return khjl;
	}

	public void setKhjl(String khjl) {
		this.khjl = khjl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}


}
