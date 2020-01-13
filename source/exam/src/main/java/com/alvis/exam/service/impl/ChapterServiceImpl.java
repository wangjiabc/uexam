package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Chapter;
import com.alvis.exam.repository.ChapterMapper;
import com.alvis.exam.service.ChapterService;
import com.alvis.exam.viewmodel.admin.user.ChapterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 章节管理impl
 * @author yangsy
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public void insertChapter(ChapterVM chapter) {

        this.chapterMapper.insertChapter(chapter);
    }

    @Override
    public void updateChapter(ChapterVM chapter) {
        this.chapterMapper.updateChapter(chapter);
    }

    @Override
    public void deleteChapterById(Integer id,Integer state) {
        this.chapterMapper.deleteChapterById(id,state);
    }

    @Override
    public List<Chapter> queryChapter(Integer typeId) {
       List<Chapter> list= this.chapterMapper.queryChapter(typeId);
        return list;
    }

    @Override
    public Chapter getNextSequence(Integer typeId) {
        return this.chapterMapper.getNextSequence(typeId);
    }

    @Override
    public Chapter findChapterById(Integer chapterId) {
        return chapterMapper.findChapterById(chapterId);
    }

}
