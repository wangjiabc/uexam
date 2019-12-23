package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.repository.ChapterMapper;
import com.alvis.exam.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 章节管理impl
 * @author yangsy
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;


    @Override
    public void insertChapter(Chapter chapter) {
        this.chapterMapper.insertChapter(chapter);
    }

    @Override
    public void updateChapter(Chapter chapter) {
        this.chapterMapper.updateChapter(chapter);
    }

    @Override
    public void deleteChapterByIds(Integer id) {
        this.chapterMapper.deleteChapterByIds(id);
    }

    @Override
    public List<Chapter> queryChapter(Integer typeId) {
       List<Chapter> list= this.chapterMapper.queryChapter(typeId);
        return list;
    }
}
