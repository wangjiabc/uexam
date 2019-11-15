package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ViewPager;
import com.alvis.exam.repository.ViewPagerMapper;
import com.alvis.exam.service.ViewPagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ViewPagerServiceImpl implements ViewPagerService {

    @Resource
    private ViewPagerMapper viewPagerMapper;

    @Override
    public Integer insert(ViewPager viewPager) {
        int insert = viewPagerMapper.insert(viewPager);
        return insert;
    }

    @Override
    public void trunCate() {
        List<ViewPager> list = viewPagerMapper.findAll();
        for (ViewPager viewPager : list) {
            Integer id = viewPager.getId();
            viewPagerMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<ViewPager> findAll() {
        List<ViewPager> all = viewPagerMapper.findAll();
        return all;
    }

    @Override
    public Integer deleteImages(Integer id) {
        int i = viewPagerMapper.deleteByPrimaryKey(id);
        return i;
    }
}
