package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.repository.ArticleTypeMapper;
import com.alvis.exam.service.UploadService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {


    @Resource
    private ArticleTypeMapper articleTypeMapper;
    @Resource
    private ArticleMapper articleMapper;


    /**
     * 动态查看文章类型
     * @return
     */
    @Override
    public List<ArticleType> findArticleType() {
        List<ArticleType> types = articleTypeMapper.findAll();
        return types;
    }

    @Override
    public void saveArticle(Article article) {
        article.setUploadDay(new Date());     //上传时间
        articleMapper.insert(article);
    }

    /**
     * 动态查看所有数据
     * @return
     */
    @Override
    public PageInfo<ArticleType> pageList(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleTypeMapper.page(requestVM)
        );
    }

    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.page(requestVM)
        );
    }


    @Override
    public String saveArticleType(ArticleType articleType) {

        int insert = articleTypeMapper.insert(articleType);
        if(insert != 0){
            return "上传成功";
        }
        return "上传失败";
    }

    @Override
    public void updateType(ArticleType articleType) {
        articleTypeMapper.updateType(articleType);
    }

    @Override
    public void deleteType(ArticleType articleType) {
        articleTypeMapper.deleteType(articleType);
    }

    @Override
    public void deleteArticle(Article article) {
        articleMapper.deleteArticle(article);
    }

    @Override
    public void updateArticleType(ArticleType articleType) {
        articleTypeMapper.updateArticleType(articleType);
    }
}
