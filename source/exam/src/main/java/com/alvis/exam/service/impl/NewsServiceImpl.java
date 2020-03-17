package com.alvis.exam.service.impl;

import com.alvis.exam.domain.News;
import com.alvis.exam.repository.NewsMapper;
import com.alvis.exam.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;
    @Override
    public void insert(News news) {
        newsMapper.insert(news);
    }

    @Override
    public void delete(Integer id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public List<News> select() {
        return newsMapper.selectAll();
    }
}
