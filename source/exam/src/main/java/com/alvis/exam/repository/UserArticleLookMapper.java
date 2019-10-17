package com.alvis.exam.repository;

import com.alvis.exam.domain.UserArticleLook;

public interface UserArticleLookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserArticleLook record);

    int insertSelective(UserArticleLook record);

    UserArticleLook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserArticleLook record);

    int updateByPrimaryKeyWithBLOBs(UserArticleLook record);

    int updateByPrimaryKey(UserArticleLook record);
}