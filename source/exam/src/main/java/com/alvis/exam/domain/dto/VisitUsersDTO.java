package com.alvis.exam.domain.dto;

import lombok.Data;

@Data
public class VisitUsersDTO {

    private Integer usersId;
    private Integer id;     //也是usersId的替代
    private String name;
    private String idNumber;
    private String license;
    private String address;
    private String wgs84Lng;
    private String wgs84Lat;
    private Integer positionId;
    private String lat;
    private String lng;

}
