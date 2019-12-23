package com.alvis.exam.repository;

import com.alvis.exam.domain.ExamType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 试卷分类
 * @author yangsy
 */

@Mapper
public interface ExamTypeMapper extends BaseMapper<ExamType> {

    /**
     * 查询试卷分类
     * @param
     */
    List<ExamType> queryExamType();
}
