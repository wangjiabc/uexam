package com.alvis.exam.repository;

import com.alvis.exam.domain.ReadState;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadStateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReadState record);

    int insertSelective(ReadState record);

    ReadState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReadState record);

    int updateByPrimaryKey(ReadState record);

    List<ReadState> selectByUseId(Integer useId);
}