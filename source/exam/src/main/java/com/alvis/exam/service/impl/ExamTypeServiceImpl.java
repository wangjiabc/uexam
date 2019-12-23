package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ExamType;
import com.alvis.exam.repository.ExamTypeMapper;
import com.alvis.exam.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷类型IMPL
 * @author yangsy
 */
@Service
public class ExamTypeServiceImpl implements ExamTypeService {
    @Autowired
    private ExamTypeMapper examTypeMapper;

    @Override
    public List<ExamType> queryExamType(ExamType examType) {
        List<ExamType> list=this.examTypeMapper.queryExamType(examType);
        return list;
    }
}
