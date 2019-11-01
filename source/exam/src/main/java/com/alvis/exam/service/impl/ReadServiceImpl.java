package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Article;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.repository.ArticleMapper;
import com.alvis.exam.service.ReadService;
import com.alvis.exam.viewmodel.admin.article.ArticleVM;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadServiceImpl implements ReadService {

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public PageInfo<Article> page(MessagePageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                articleMapper.page(requestVM)
        );
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }
}
