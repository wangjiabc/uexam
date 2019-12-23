package com.alvis.exam.service;

import com.alvis.exam.domain.Chapter;

import java.util.List;

/**
 * @author yangsy
 * 章节管理
 */
public interface ChapterService {
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
    void deleteChapterById(Integer id);

    /**
     * queryChapter
     * 查询章节
     * @param typeId
     */
    List<Chapter> queryChapter(Integer typeId);
}
