package com.alvis.exam.service.impl;

import com.alvis.exam.domain.*;
import com.alvis.exam.repository.saleDataMapper;
import com.alvis.exam.service.saleDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 智慧收银机进度（传分页参数）
     * @param cashregist
     * @param
     * @return
     */
    @Override
    public PageInfo<cashregister> queryCashregister(cashregister cashregist) {
        return PageHelper.startPage(cashregist.getPageIndex(),cashregist.getPageSize(),"id desc").doSelectPageInfo(() ->
                saleDataMapper.queryCashregister(cashregist)
        );
    }

    /**
     * 本月完成进度
     */
    @Override
    public saleProgress querysaleProgress(String manager, String month) {
        return this.saleDataMapper.querysaleProgress(manager,month);
    }

    /**
     *红码管家
     */
    @Override
    public List<logSituation> querylogSituation(String customerManager, String status, String phoneOrder) {
        return this.saleDataMapper.querylogSituation(customerManager,status,phoneOrder);
    }

    //////////////////////给前端提供参数//////////////////////////////

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    @Override
    public List<String> queryBrankManager() {
        return this.saleDataMapper.queryBrankManager();
    }

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    @Override
    public List<String> queryBrankType() {
        return this.saleDataMapper.queryBrankType();
    }

    /**
     * 本月完成进度(返回出参数客户经理)
     */
    @Override
    public List<String> queryProgressManager() {
        return this.saleDataMapper.queryProgressManager();
    }
}
