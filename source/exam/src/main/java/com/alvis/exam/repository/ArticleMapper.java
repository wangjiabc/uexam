package com.alvis.exam.repository;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Message;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.article.ArticleDto;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> findAll();

    List<Article> page(MessagePageRequestVM requestVM);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    List<Article> findArticle(Integer typeId);

    List<Article> studentPage(ArticleDto articleDto);

    Article selectById(Article article);

    List<Article> articlePage(ArticleDto articleDto);

}