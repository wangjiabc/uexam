package com.alvis.exam.repository;

import com.alvis.exam.domain.ExamType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    ExamType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);

    Integer findExamTypeIdByName(String examTypeName);

    List<ExamType> findAll();

    ExamType findNameByExamTypeId(Integer examTypeId);

    /**
     * 查询试卷分类
     * @param
     */
    List<ExamType> queryExamType();
}
