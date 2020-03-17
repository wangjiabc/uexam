package com.alvis.exam.repository;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 思维导图
 * @author yangsy
 */

@Mapper
public interface DataQueryMapper extends BaseMapper<SourcePut> {

    /**
     * 分页查询本轮货源投放
     * @param sourcePut
     */
    List<SourcePut> querySourcePut(SourcePutVM sourcePut);

    /**
     * 根据个人ID 查询本月完成进度
     */
    List<String> queryCompletionSchedule(String uuid);

    /**
     * 根据个人ID 查询本月完成总销
     */
    SaleData queryTotalSale(String uuid);


}
