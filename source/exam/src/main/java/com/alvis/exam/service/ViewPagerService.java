package com.alvis.exam.service;

import com.alvis.exam.domain.ViewPager;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ViewPagerService {
    void insert(ViewPager viewPager);

    void trunCate();

    List<ViewPager> findAll();

    void deleteImages(Integer id);
}
