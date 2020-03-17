package com.alvis.exam.repository;

import com.alvis.exam.domain.fxdhqkbFgsPqxsjdb;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface fxdhqkbFgsPqxsjdbMapper {
    int insert(fxdhqkbFgsPqxsjdb record);

    int insertSelective(fxdhqkbFgsPqxsjdb record);

    List<fxdhqkbFgsPqxsjdb> findBasic(MessageRequestVM requestVM);

    fxdhqkbFgsPqxsjdb findDetails(String khbh);

    Double findDYJE(String khjl);

    List<String> findManager();

    Integer findCount(String khjl);

    Double findDYJE1();
    Integer findCount1();
}