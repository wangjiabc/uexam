package com.alvis.exam.service;

import com.alvis.exam.domain.Source;
import com.alvis.exam.domain.brank;
import com.alvis.exam.domain.cashregister;
import com.alvis.exam.domain.sweepcode;
import com.github.pagehelper.PageInfo;

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
    PageInfo<cashregister> queryCashregister(cashregister cashregist);



}
