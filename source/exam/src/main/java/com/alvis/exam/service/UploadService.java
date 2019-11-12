package com.alvis.exam.service;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UploadService {
    //查看文章分类
    List<ArticleType> findArticleType();
    //将上传数据进行封装存储
    void saveArticle(Article article);

    //返回文章信息
    PageInfo<Article> page(MessagePageRequestVM requestVM);

    //返回文章分类信息
    PageInfo<ArticleType> pageList(MessagePageRequestVM requestVM);

    //存储文章分类
    String saveArticleType(ArticleType articleType);

    //修改文章分类
    void updateType(ArticleType articleType);               //file修改时
    void updateArticleType(ArticleType articleType);        //file不修改为空时

    //删除文章分类
    void deleteType(ArticleType articleType);

    //删除文章
    void deleteArticle(Article article);

}
