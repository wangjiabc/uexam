package com.alvis.exam.viewmodel.admin.user;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

import java.util.Date;

/**
 * 用户
 */
public class UserVM extends MessageRequestVM {

    private static final long serialVersionUID = -7797183521247423117L;

    private Integer id;

    private String userUuid;

    private String userName;

    private String password;

    private String realName;

    private Integer age;

    private Integer sex;

    private Date birthDay;

    private Integer userLevel;

    private String phone;

    private Integer role;

    private Integer status;

    private String imagePath;

    private Date createTime;

    private Date modifyTime;

    private Date lastActiveTime;

    private Boolean deleted;

    private String wxOpenId;

    private String telphone;

    private String duty;

    private Integer wxRole;

    private Integer isLook;

    private Integer monthSaleNorm;

    private Integer quarterSaleNorm;

    public Integer getIsLook() {
        return isLook;
    }

    public void setIsLook(Integer isLook) {
        this.isLook = isLook;
    }

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

    public Integer getWxRole() {
        return wxRole;
    }

    public void setWxRole(Integer wxRole) {
        this.wxRole = wxRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId == null ? null : wxOpenId.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }
}
