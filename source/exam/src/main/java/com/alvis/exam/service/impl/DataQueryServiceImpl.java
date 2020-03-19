package com.alvis.exam.service.impl;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.WideNarrow;
import com.alvis.exam.repository.DataQueryMapper;
import com.alvis.exam.service.DataQueryService;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.alvis.exam.viewmodel.admin.user.UserVM;
import com.alvis.exam.viewmodel.admin.user.WideNarrowVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据查询
 * @author yangsy
 */
@Service
public class DataQueryServiceImpl implements DataQueryService {

    @Autowired
    private DataQueryMapper dataQueryMapper;

    @Override
    public User queryuser(String wxOpenId) {
        return this.dataQueryMapper.queryuser(wxOpenId);
    }

    /**
     * 分页查询本轮货源投放
     */
    @Override
    public PageInfo<SourcePut> querySourcePut(SourcePutVM sourcePut) {
        return PageHelper.startPage(sourcePut.getPageIndex(),sourcePut.getPageSize(),"id desc").doSelectPageInfo(() ->
                dataQueryMapper.querySourcePut(sourcePut)
        );
    }

    /**
     * 根据个人ID 查询本月完成进度
     */
    @Override
    public List<Map<String,Object>> queryCompletionSchedule(String uuid){
        return this.dataQueryMapper.queryCompletionSchedule(uuid);
    }


    /**
     * 查询阶段完成进度
     */
    @Override
    public List<Map<String, Object>> queryCompletionStage(String uuid) {
        return this.dataQueryMapper.queryCompletionStage(uuid);
    }

    /**
     * 根据个人ID 查询本月完成总销
     */
    public SaleData queryTotalSale(String uuid){
        return this.dataQueryMapper.queryTotalSale(uuid);
    }

    /**
     * 本月完成进度宽窄
     */
    @Override
    public WideNarrow queryWideNarrow(String uuid) {
        return this.dataQueryMapper.queryWideNarrow(uuid);
    }

    /**
     * 本月完成进度宽窄（管理员）
     */
    @Override
    public PageInfo<WideNarrow> queryWideNarrowMag(WideNarrowVM wideNarrow) {
        return PageHelper.startPage(wideNarrow.getPageIndex(),wideNarrow.getPageSize(),"id desc").doSelectPageInfo(() ->
                dataQueryMapper.queryWideNarrowMag(wideNarrow)
        );
    }

    /**
     * 阶段性考核指标（管理员）
     */
    @Override
    public PageInfo<User> queryuserMge(UserVM user) {
        return PageHelper.startPage(user.getPageIndex(),user.getPageSize(),"id desc").doSelectPageInfo(() ->
                dataQueryMapper.queryuserMge(user)
        );
    }


    /**
     * 查询本月完成进度(管理员)
     */
    @Override
    public List<Map<String, Object>> queryCompletionScheduleMGE(String uuid) {
        return this.dataQueryMapper.queryCompletionScheduleMGE(uuid);
    }

    /**
     * 查询阶段完成进度（管理员）
     */
    @Override
    public List<Map<String, Object>> queryCompletionStageMGE(String uuid) {
        return this.dataQueryMapper.queryCompletionStageMGE(uuid);
    }
}
