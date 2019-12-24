package com.alvis.exam.repository;

import com.alvis.exam.domain.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);

    List<String> findByTypeId(Integer typeId);

    Integer findChapterIdByName(String chapter);

    List<Chapter> findAllByTypeId(Integer typeId);

    String findNameByChapterId(Integer chapterId);

}