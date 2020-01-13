package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.dto.ExamPaperDTO;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.repository.ChapterMapper;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.domain.dto.ArticleDTO;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTypeMapper articleTypeMapper;
    @Resource
    private ChapterMapper chapterMapper;



    /**
     * 动态查看文章分类
     * @return
     */
    @Override
    public List<ArticleType> findArticleType() {
        List<ArticleType> types = articleTypeMapper.findAll();
        return types;
    }

    @Override
    public void saveArticle(Article articleVM) {
        articleVM.setUploadDay(new Date());     //上传时间
        articleMapper.insert(articleVM);
    }

    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "type_id desc").doSelectPageInfo(() ->
                articleMapper.page(requestVM)
        );
    }

    @Override
    public void deleteArticle(Article article) {
        articleMapper.deleteArticle(article);
    }

    @Override
    public String getConText(Article article) {
        return articleMapper.selectByPrimaryKey(article.getId()).getContent();
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<Article> findArticle(Integer typeId) {
//        Integer typeId = articleType.getId();
        List<Article> list = articleMapper.findArticle(typeId);
        return list;
    }

    @Override
    public PageInfo<com.alvis.exam.domain.dto.article.ArticleDTO> studentPage(Integer typeId, MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(typeId);
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.studentPage(articleDto)
        );
    }

    @Override
    public PageInfo<ExamPaperDTO> secondLevel(Integer typeId,MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(typeId);
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize(), "tc.sequence desc").doSelectPageInfo(() ->
                chapterMapper.findExamPaperDTOByTypeId(articleDto)
        );
    }

    @Override
    public PageInfo<ExamPaperDTO> secondLevel1(int typeId, MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(typeId);
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize()).doSelectPageInfo(() ->
                chapterMapper.findExamPaperDTOByTypeId1(articleDto)
        );
    }

    @Override
    public PageInfo<ExamPaperDTO> secondLevel2(int typeId, MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(typeId);
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize()).doSelectPageInfo(() ->
                chapterMapper.findExamPaperDTOByTypeId2(articleDto)
        );
    }


    @Override
    public Article findIsExitByChapterId(Integer chapterId) {
        return articleMapper.findIsExitByChapterId(chapterId);
    }

    @Override
    public List<Integer> findList() {
        return articleMapper.findList();
    }


    @Override
    public PageInfo<com.alvis.exam.domain.dto.article.ArticleDTO> chapterPage(Integer typeId, Integer chapterId, MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(typeId);
        articleDto.setChapterId(chapterId);
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.chapterPage(articleDto)
        );
    }



    /**
     * 根据文章id查询文章内容
     * @param id
     * @return
     */
    @Override
    public String findDetails(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        String content = article.getContent();
        return content;
    }

    /**
     * 根据文章id查询文章
     * @param id
     * @return
     */
    @Override
    public Article find(Integer id) {
        Article article = articleMapper.selectById(id);
        return article;
    }

    /**
     * 获取所有文章id
     * @return
     */
    @Override
    public List<Integer> findArticleId() {
        List<Article> all = articleMapper.findAll();
        List<Integer> arrayList = new ArrayList<>();
        for (Article article : all) {
            Integer id = article.getId();
            arrayList.add(id);
        }
        return arrayList;
    }

    @Override
    public PageInfo<Article> articlePage(Integer state,MessageRequestVM requestVM) {
        ArticleDTO articleDto = new ArticleDTO();
        articleDto.setTypeId(state);
        articleDto.setReceiveUserId(requestVM.getReceiveUserId());
        articleDto.setPageIndex(requestVM.getPageIndex());
        articleDto.setPageSize(requestVM.getPageSize());

        return PageHelper.startPage(articleDto.getPageIndex(), articleDto.getPageSize(), "id desc").doSelectPageInfo(() ->
                    articleMapper.articlePage(articleDto)
            );
    }

    @Override
    public void trunCate() {

    }

    @Override
    public Integer findChapter(String chapter) {
        Integer id = chapterMapper.findChapterIdByName(chapter);
        return id;
    }

    @Override
    public Article findByName(String title) {
        return articleMapper.findByName(title);
    }
}
