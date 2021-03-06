package com.alvis.exam.service;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.WideNarrow;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.alvis.exam.viewmodel.admin.user.UserVM;
import com.alvis.exam.viewmodel.admin.user.WideNarrowVM;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yangsy
 */
public interface DataQueryService {

    /**
     * 根据openId查询用户uuid
     */
    User queryuser(String wxOpenId);

    /**
     * querySourcePut
     * 分页查询本轮货源投放
     * @param sourcePut
     */
    PageInfo<SourcePut> querySourcePut(SourcePutVM sourcePut);

    /**
     * 查询本月完成进度
     */
    List<Map<String,Object>> queryCompletionSchedule(String uuid);

    /**
     * 查询阶段完成进度
     */
    List<Map<String,Object>> queryCompletionStage(String uuid);

    /**
     * 根据个人ID 查询本月完成总销
     */
    SaleData queryTotalSale(String uuid);

    /**
     * 本月完成进度宽窄
     */
    WideNarrow queryWideNarrow(String uuid);

    /**
     * 本月完成进度宽窄（管理员）
     */
    PageInfo<WideNarrow> queryWideNarrowMag(WideNarrowVM wideNarrow);

    /**
     * 阶段性考核指标（管理员）
     */
    PageInfo<User> queryuserMge(UserVM user);

    /**
     * 查询本月完成进度(管理员)
     */
    List<Map<String,Object>> queryCompletionScheduleMGE(String uuid);

    /**
     * 查询阶段完成进度（管理员）
     */
    List<Map<String,Object>> queryCompletionStageMGE(String uuid);

}
