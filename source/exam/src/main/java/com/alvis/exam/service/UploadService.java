package com.alvis.exam.service;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.domain.Message;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

public interface UploadService {
    //动态查看文章类型
    List<ArticleType> findArticleType();
    //将上传数据进行封装存储
    String saveArticle(ArticleVM articleVM);
    //动态查看文章所有数据
//    List<Article> findAll(MessagePageRequestVM requestVM);

    PageInfo<Article> page(MessagePageRequestVM requestVM);

    PageInfo<ArticleType> pageList(MessagePageRequestVM requestVM);

    public String saveArticle(ArticleType articleType);
}
