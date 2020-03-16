package com.alvis.exam.service;

import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.domain.fxdhqkbFgsPqxsjdb;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;


public interface SaleService {
    PageInfo<FxdhqkbFgsKhxxcx> findBasic(MessageRequestVM requestVM);

    FxdhqkbFgsKhxxcx findDetails(Integer xh);

    PageInfo<fxdhqkbFgsPqxsjdb> findB2(MessageRequestVM messageRequestVM);

    fxdhqkbFgsPqxsjdb findD2(String khbh);

    Double findDYJE(String khjl);

    List<String> findManager();

    Integer findCount(String s);

    Double findDYJE1();

    Integer findCount1();
}
