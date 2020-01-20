package com.alvis.exam.repository;

import com.alvis.exam.domain.VisitedUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitedUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitedUsers record);

    int insertSelective(VisitedUsers record);

    VisitedUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitedUsers record);

    int updateByPrimaryKey(VisitedUsers record);

    void updateByVisitedUsers(VisitedUsers visitedUsers);

    VisitedUsers findTime(VisitedUsers visitedUsers);

    VisitedUsers findMaxTime(VisitedUsers visitedUsers);

    List<Integer> findUsersId(VisitedUsers visitedUsers);
}