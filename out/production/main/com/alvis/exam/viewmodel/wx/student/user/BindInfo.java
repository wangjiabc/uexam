package com.alvis.exam.viewmodel.wx.student.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BindInfo {

    private String userName;

//    private String password;

    @NotBlank
	private String code;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
