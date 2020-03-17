package com.alvis.exam.service;

import com.alvis.exam.domain.News;

import java.util.List;

public interface NewsService {
    void insert(News news);

    void delete(Integer id);

    void update(News news);

    List<News> select();
}
