package com.alvis.exam.service;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.viewmodel.admin.user.ChapterVM;

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
    void insertChapter(ChapterVM chapter);

    /**
     * updateChapter
     *修改章节
     * @param chapter
     */
    void updateChapter(ChapterVM chapter);

    /**
     * deleteChapterByIds
     *删除章节
     * @param id
     */
    void deleteChapterById(Integer id, Integer state);

    /**
     * queryChapter
     * 查询章节
     * @param typeId
     */
    List<Chapter> queryChapter(Integer typeId);

    /**
     * 获取下一排序号
     */
    Chapter getNextSequence(Integer typeId);

    Chapter findChapterById(Integer chapterId);
}
