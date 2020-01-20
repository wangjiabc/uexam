package com.alvis.exam.domain;

import java.io.Serializable;

public class Position implements Serializable {
    private String id;

    private String license;

    private String isLicense;

    private String checkId;

    private String neatenId;

    private String province;

    private String city;

    private String district;

    private String street;

    private String streetNumber;

    private String lng;

    private String lat;

    private String wgs84Lng;

    private String wgs84Lat;

    private String date;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getIsLicense() {
        return isLicense;
    }

    public void setIsLicense(String isLicense) {
        this.isLicense = isLicense == null ? null : isLicense.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    public String getNeatenId() {
        return neatenId;
    }

    public void setNeatenId(String neatenId) {
        this.neatenId = neatenId == null ? null : neatenId.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getWgs84Lng() {
        return wgs84Lng;
    }

    public void setWgs84Lng(String wgs84Lng) {
        this.wgs84Lng = wgs84Lng == null ? null : wgs84Lng.trim();
    }

    public String getWgs84Lat() {
        return wgs84Lat;
    }

    public void setWgs84Lat(String wgs84Lat) {
        this.wgs84Lat = wgs84Lat == null ? null : wgs84Lat.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}