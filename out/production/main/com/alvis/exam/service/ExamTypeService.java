package com.alvis.exam.service;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.domain.ExamType;

import java.util.List;

/**
 * @author yangsy
 * 试卷类型
 */
public interface ExamTypeService {
    /**
     * queryExamType
     * 查询试卷类型
     * @param
     */
    List<ExamType> queryExamType();
}
