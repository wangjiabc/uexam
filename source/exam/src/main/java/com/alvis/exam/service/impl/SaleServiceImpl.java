package com.alvis.exam.service.impl;

import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.domain.fxdhqkbFgsPqxsjdb;
import com.alvis.exam.repository.FxdhqkbFgsKhxxcxMapper;
import com.alvis.exam.repository.fxdhqkbFgsPqxsjdbMapper;
import com.alvis.exam.repository.FxdhqkbFgsKhxxcxMapper;
import com.alvis.exam.service.SaleService;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private FxdhqkbFgsKhxxcxMapper fgsKhxxcxMapper;
    @Resource
    private fxdhqkbFgsPqxsjdbMapper fgsPqxsjdbMapper;

    @Override
    public PageInfo<FxdhqkbFgsKhxxcx> findBasic(MessageRequestVM requestVM) {

        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "xh desc").doSelectPageInfo(() ->
                fgsKhxxcxMapper.findBasic()
        );
    }

    @Override
    public FxdhqkbFgsKhxxcx findDetails(Integer xh) {
        return fgsKhxxcxMapper.findDetails(xh);
    }

    @Override
    public PageInfo<fxdhqkbFgsPqxsjdb> findB2(MessageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "khbh desc").doSelectPageInfo(() ->
                fgsPqxsjdbMapper.findBasic(requestVM)
        );
    }

    @Override
    public fxdhqkbFgsPqxsjdb findD2(String khbh) {
        return fgsPqxsjdbMapper.findDetails(khbh);
    }

    @Override
    public Double findDYJE(String khjl ) {
        return fgsPqxsjdbMapper.findDYJE(khjl);
    }

    @Override
    public List<String> findManager() {
        return fgsPqxsjdbMapper.findManager();
    }

    @Override
    public Integer findCount(String s) {
        return fgsPqxsjdbMapper.findCount(s);
    }

    @Override
    public Double findDYJE1() {
        return fgsPqxsjdbMapper.findDYJE1();
    }

    @Override
    public Integer findCount1() {
        return fgsPqxsjdbMapper.findCount1();
    }
}
