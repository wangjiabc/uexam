package com.alvis.exam.service;

import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Chapter;
import com.alvis.exam.domain.ExamType;
import com.alvis.exam.domain.Subject;
import com.alvis.exam.viewmodel.admin.education.SubjectPageRequestVM;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;

public interface SubjectService extends BaseService<Subject> {

    List<Subject> getSubjectByLevel(Integer level);

    List<Subject> allSubject();

    Integer levelBySubjectId(Integer id);

    PageInfo<Subject> page(SubjectPageRequestVM requestVM);

    List<ExamType> allExamType();

    List<ArticleType> findArticleType();

    List<Chapter> findChapterById(Integer typeId);

    ExamType findNameByExamTypeId(Integer examTypeId);
}
