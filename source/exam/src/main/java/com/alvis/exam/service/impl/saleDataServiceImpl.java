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
     * @param
     * @param
     * @return
     */
    @Override
    public List<cashregister> queryCashregister() {
       return this.saleDataMapper.queryCashregister();
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

    /**
     * 分页查询本轮货源投放明细(返回出参数客户经理)
     */
    @Override
    public List<String> querySourceManager() {
        return this.saleDataMapper.querySourceManager();
    }

    /**
     * 扫码进度(返回出参数客户经理)
     */
    @Override
    public List<String> querycodeManager() {
        return this.saleDataMapper.querycodeManager();
    }


    /**
     * 红码管家（传客户经理和状态和手机订烟）
     */
    @Override
    public List<String> querylogManager() {
        return this.saleDataMapper.querylogManager();
    }
}
