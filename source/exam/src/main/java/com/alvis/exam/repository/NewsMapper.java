package com.alvis.exam.repository;

import com.alvis.exam.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    List<News> selectLimit(); //查询所有  用于小程序上  只查了最新十条

    List<News> page();      //管理后台查询所有  进行了分页

}