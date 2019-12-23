package com.alvis.exam.service;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.domain.ExamType;

import java.util.List;

/**
 * @author yangsy
 * 章节管理
 */
public interface ExamTypeService {
    /**
     * queryExamType
     * 查询章节
     * @param examType
     */
    List<ExamType> queryExamType(ExamType examType);
}
