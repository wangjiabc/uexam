package com.alvis.exam.service.impl;

import com.alvis.exam.domain.News;
import com.alvis.exam.repository.NewsMapper;
import com.alvis.exam.service.NewsService;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public List<News> selectLimit() {
        return newsMapper.selectLimit();
    }

    @Override
    public News findById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    /**
     * 管理后台展示新闻
     * @param model
     * @return
     */
    @Override
    public PageInfo<News> selectAll(MessagePageRequestVM model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize()).doSelectPageInfo(() ->
                newsMapper.page()
        );
    }
}
