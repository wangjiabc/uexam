package com.alvis.exam.domain;

import java.io.Serializable;

public class UserLicense implements Serializable {
    private String openId;

    private String phone;

    private String license;

    private String address;

    private String authentication;

    private String authenDate;

    private String licenseStartDate;

    private String licenseEndDate;

    private String region;

    private String date;

    private String businessState;

    private String businessDate;

    private String weight;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    private Integer area;

    private String stopStartDate;

    private String stopEndData;

    private String applicationDate;

    private static final long serialVersionUID = 1L;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication == null ? null : authentication.trim();
    }

    public String getAuthenDate() {
        return authenDate;
    }

    public void setAuthenDate(String authenDate) {
        this.authenDate = authenDate == null ? null : authenDate.trim();
    }

    public String getLicenseStartDate() {
        return licenseStartDate;
    }

    public void setLicenseStartDate(String licenseStartDate) {
        this.licenseStartDate = licenseStartDate == null ? null : licenseStartDate.trim();
    }

    public String getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(String licenseEndDate) {
        this.licenseEndDate = licenseEndDate == null ? null : licenseEndDate.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState == null ? null : businessState.trim();
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate == null ? null : businessDate.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getStopStartDate() {
        return stopStartDate;
    }

    public void setStopStartDate(String stopStartDate) {
        this.stopStartDate = stopStartDate == null ? null : stopStartDate.trim();
    }

    public String getStopEndData() {
        return stopEndData;
    }

    public void setStopEndData(String stopEndData) {
        this.stopEndData = stopEndData == null ? null : stopEndData.trim();
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate == null ? null : applicationDate.trim();
    }
}