package com.alvis.exam.service;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.ReadState;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReadService {


    PageInfo<Article> page(MessagePageRequestVM requestVM);

    List<Article> findAll();

    void saveReadState(ReadState readState);

    int findIntegrate(Integer useId);
}
