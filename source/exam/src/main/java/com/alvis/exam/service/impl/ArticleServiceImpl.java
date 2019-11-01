package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.service.ArticleService;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTypeMapper articleTypeMapper;



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
    public void saveArticle(ArticleVM articleVM) {
        articleVM.setUploadDay(new Date());     //上传时间
        articleMapper.insert(articleVM);
    }

    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
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
        articleMapper.updateByPrimaryKey(article);
    }
}
