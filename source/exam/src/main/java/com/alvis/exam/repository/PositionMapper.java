package com.alvis.exam.repository;

import com.alvis.exam.domain.Position;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper {
    int insert(Position record);

    int insertSelective(Position record);
}