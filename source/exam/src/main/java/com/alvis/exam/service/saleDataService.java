package com.alvis.exam.service;

import com.alvis.exam.domain.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangsy
 */
public interface saleDataService {
    /**
     * querySourcePut
     * 分页查询本轮货源投放
     * @param source
     */
    PageInfo<Source> querySource(Source source);

    /**
     * 查询宽窄系列指标
     */
    brank queryBrank(String manager, String type);

    /**
     * 查询扫码进度
     */
    sweepcode querySweepcode(String manager);

    /**
     * 智慧收银机进度
     */
    List<cashregister> queryCashregister();

    /**
     * 本月完成进度
     */
    saleProgress querysaleProgress(String manager, String month);

    /**
     *红码管家
     */
    List<logSituation> querylogSituation(String customerManager, String status, String phoneOrder);




    //////////////////////给前端提供参数//////////////////////////////

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    List<String> queryBrankManager();

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    List<String> queryBrankType();

    /**
     * 本月完成进度(返回出参数客户经理)
     */
    List<String> queryProgressManager();

    /**
     * 分页查询本轮货源投放明细(返回出参数客户经理)
     */
    List<String> querySourceManager();

    /**
     * 扫码进度(返回出参数客户经理)
     */
    List<String> querycodeManager();

    /**
     * 红码管家（传客户经理和状态和手机订烟）
     */
    List<String> querylogManager();


}
