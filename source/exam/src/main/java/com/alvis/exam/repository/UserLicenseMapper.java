package com.alvis.exam.repository;

import com.alvis.exam.domain.UserLicense;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLicenseMapper {
    int insert(UserLicense record);

    int insertSelective(UserLicense record);
}