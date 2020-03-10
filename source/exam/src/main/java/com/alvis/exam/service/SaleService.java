package com.alvis.exam.service;

import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface SaleService {
    PageInfo<FxdhqkbFgsKhxxcx> findBasic(MessageRequestVM requestVM);

    FxdhqkbFgsKhxxcx findDetails(Integer xh);
}
