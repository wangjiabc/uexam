package com.alvis.exam.service.impl;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.repository.DataQueryMapper;
import com.alvis.exam.service.DataQueryService;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
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
public class DataQueryServiceImpl implements DataQueryService {

    @Autowired
    private DataQueryMapper dataQueryMapper;

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
    public List<String> queryCompletionSchedule(String uuid){
        return this.dataQueryMapper.queryCompletionSchedule(uuid);
    }

    /**
     * 根据个人ID 查询本月完成总销
     */
    public SaleData queryTotalSale(String uuid){
        return this.dataQueryMapper.queryTotalSale(uuid);
    }
}
