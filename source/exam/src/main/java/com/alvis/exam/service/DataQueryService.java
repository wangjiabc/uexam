package com.alvis.exam.service;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangsy
 */
public interface DataQueryService {
    /**
     * querySourcePut
     * 分页查询本轮货源投放
     * @param sourcePut
     */
    PageInfo<SourcePut> querySourcePut(SourcePutVM sourcePut);

    /**
     * 根据个人ID 查询本月完成进度
     */
    List<String> queryCompletionSchedule(String uuid);

    /**
     * 根据个人ID 查询本月完成总销
     */
    SaleData queryTotalSale(String uuid);


}
