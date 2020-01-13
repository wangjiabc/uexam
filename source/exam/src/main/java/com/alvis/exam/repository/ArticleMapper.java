package com.alvis.exam.repository;

import com.alvis.exam.domain.Article;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.domain.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article article);

    List<Article> findAll();

    List<Article> page(MessagePageRequestVM requestVM);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    List<Article> findArticle(Integer typeId);

    List<com.alvis.exam.domain.dto.article.ArticleDTO> studentPage(ArticleDTO articleDto);

    List<com.alvis.exam.domain.dto.article.ArticleDTO> chapterPage(ArticleDTO articleDto);

    Article selectById(Integer id);

    List<Article> articlePage(ArticleDTO articleDto);

    Article findByName(String title);

    Article findIsExitByChapterId(Integer chapterId);

    List<Integer> findList();
}