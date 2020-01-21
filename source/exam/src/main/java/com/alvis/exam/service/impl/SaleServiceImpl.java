package com.alvis.exam.service.impl;

import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.repository.FxdhqkbFgsKhxxcxMapper;
import com.alvis.exam.service.SaleService;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private FxdhqkbFgsKhxxcxMapper fgsKhxxcxMapper;

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
}
