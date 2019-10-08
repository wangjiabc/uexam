package com.alvis.exam.service.impl;

import com.alvis.exam.repository.BaseMapper;
import com.alvis.exam.repository.ExamPaperAnswerMapper;
import com.alvis.exam.repository.ExamPaperMapper;
import com.alvis.exam.repository.ExamPaperQuestionCustomerAnswerMapper;
import com.alvis.exam.repository.QuestionMapper;
import com.alvis.exam.repository.SubjectMapper;
import com.alvis.exam.repository.TaskExamCustomerAnswerMapper;
import com.alvis.exam.repository.TaskExamMapper;
import com.alvis.exam.repository.TextContentMapper;
import com.alvis.exam.repository.UserEventLogMapper;
import com.alvis.exam.repository.UserMapper;
import com.alvis.exam.repository.UserTokenMapper;
import com.alvis.exam.service.BaseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    BaseMapper<T> baseMapper;

    public BaseServiceImpl(ExamPaperAnswerMapper examPaperAnswerMapper) {
		// TODO Auto-generated constructor stub
    	baseMapper=(BaseMapper<T>) examPaperAnswerMapper;
	}

	public BaseServiceImpl(ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) examPaperQuestionCustomerAnswerMapper;
	}

	public BaseServiceImpl(ExamPaperMapper examPaperMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) examPaperMapper;
	}

	public BaseServiceImpl(QuestionMapper questionMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) questionMapper;
	}

	public BaseServiceImpl(SubjectMapper subjectMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) subjectMapper;
	}

	public BaseServiceImpl(TaskExamCustomerAnswerMapper taskExamCustomerAnswerMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) taskExamCustomerAnswerMapper;
	}

	public BaseServiceImpl(TaskExamMapper taskExamMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) taskExamMapper;
	}

	public BaseServiceImpl(TextContentMapper textContentMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) textContentMapper;
	}

	public BaseServiceImpl(UserEventLogMapper userEventLogMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) userEventLogMapper;
	}

	public BaseServiceImpl(UserMapper userMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) userMapper;
	}

	public BaseServiceImpl(UserTokenMapper userTokenMapper) {
		// TODO Auto-generated constructor stub
		baseMapper=(BaseMapper<T>) userTokenMapper;
	}

	@Override
    public int deleteById(Integer id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public int insertByFilter(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public T selectById(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdFilter(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
}
