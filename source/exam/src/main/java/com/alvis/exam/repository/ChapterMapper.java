package com.alvis.exam.repository;

import com.alvis.exam.domain.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 章节管理
 * @author yangsy
 */

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

    /**
     * insertChapter
     *添加章节
     * @param chapter
     */
    void insertChapter(Chapter chapter);

    /**
     * updateChapter
     *修改章节
     * @param chapter
     */
    void updateChapter(Chapter chapter);

    /**
     * deleteChapterByIds
     *删除章节
     * @param id
     */
    void deleteChapterByIds(Integer id);

    /**
     * 查询思维导图
     * @param typeId
     */
    List<Chapter> queryChapter(Integer typeId);
}
