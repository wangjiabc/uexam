package com.alvis.exam.repository;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.viewmodel.admin.user.ChapterVM;
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
    void insertChapter(ChapterVM chapter);

    /**
     * updateChapter
     *修改章节
     * @param chapter
     */
    void updateChapter(ChapterVM chapter);

    /**
     * deleteChapterById
     *删除章节
     * @param id
     */
    void deleteChapterById(Integer id,Integer state);

    /**
     * 查询思维导图
     * @param typeId
     */
    List<Chapter> queryChapter(Integer typeId);

    /**
     * 获取下一排序号
     */
    Chapter getNextSequence(Integer typeId);
}
