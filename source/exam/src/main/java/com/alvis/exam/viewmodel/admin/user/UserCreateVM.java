package com.alvis.exam.viewmodel.admin.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateVM {

    private Integer id;

    @NotBlank
    private String userName;

    private String password;

    @NotBlank
    private String realName;

    private String age;

    private Integer status;

    private Integer sex;

    private String birthDay;

    private String phone;

    private Integer role;

    private Integer userLevel;

	private Integer monthSaleNorm;

	private Integer quarterSaleNorm;

	public Integer getMonthSaleNorm() {
		return monthSaleNorm;
	}

	public void setMonthSaleNorm(Integer monthSaleNorm) {
		this.monthSaleNorm = monthSaleNorm;
	}

	public Integer getQuarterSaleNorm() {
		return quarterSaleNorm;
	}

	public void setQuarterSaleNorm(Integer quarterSaleNorm) {
		this.quarterSaleNorm = quarterSaleNorm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
}
