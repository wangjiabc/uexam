package com.alvis.exam.service;

import com.alvis.exam.domain.News;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NewsService {
    void insert(News news);

    void delete(Integer id);

    void update(News news);


    PageInfo<News> selectAll(MessagePageRequestVM model);

    List<News> selectLimit();

    News findById(Integer id);
}
