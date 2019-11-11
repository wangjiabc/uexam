package com.alvis.exam.repository;

import com.alvis.exam.domain.ViewPager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ViewPagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViewPager record);

    int insertSelective(ViewPager record);

    ViewPager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViewPager record);

    int updateByPrimaryKey(ViewPager record);
}