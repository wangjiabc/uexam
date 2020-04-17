package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Source;
import com.alvis.exam.domain.brank;
import com.alvis.exam.domain.cashregister;
import com.alvis.exam.domain.sweepcode;
import com.alvis.exam.repository.saleDataMapper;
import com.alvis.exam.service.saleDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据查询
 * @author yangsy
 */
@Service
public class saleDataServiceImpl implements saleDataService {

    @Autowired
    private saleDataMapper saleDataMapper;

    /**
     * 分页查询本轮货源投放
     * @param source
     * @return
     */
    @Override
    public PageInfo<Source> querySource(Source source) {
        return PageHelper.startPage(source.getPageIndex(),source.getPageSize(),"id desc").doSelectPageInfo(() ->
                saleDataMapper.querySource(source)
        );
    }

    /**
     * 查询宽窄系列指标
     * @param manager
     * @param type
     * @return
     */
    @Override
    public brank queryBrank(String manager, String type) {
        return this.saleDataMapper.queryBrank(manager,type);
    }

    /**
     * 查询扫码进度
     * @param manager
     * @return
     */
    @Override
    public sweepcode querySweepcode(String manager) {
        return this.saleDataMapper.querySweepcode(manager);
    }

    @Override
    public PageInfo<cashregister> queryCashregister(cashregister cashregist) {
        return PageHelper.startPage(cashregist.getPageIndex(),cashregist.getPageSize(),"id desc").doSelectPageInfo(() ->
                saleDataMapper.queryCashregister(cashregist)
        );
    }
}
