package com.alvis.exam.repository;

import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FxdhqkbFgsKhxxcxMapper {
    int insert(FxdhqkbFgsKhxxcx record);

    int insertSelective(FxdhqkbFgsKhxxcx record);

    List<FxdhqkbFgsKhxxcx> findBasic();

    FxdhqkbFgsKhxxcx findDetails(Integer xh);
}