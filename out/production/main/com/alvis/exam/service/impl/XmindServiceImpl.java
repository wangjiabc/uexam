package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Xmind;
import com.alvis.exam.repository.XmindMapper;
import com.alvis.exam.service.XmindService;
import com.alvis.exam.viewmodel.admin.user.XmindVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 思维导图
 * @author yangsy
 */
@Service
public class XmindServiceImpl implements XmindService {

    @Autowired
    private XmindMapper xmindMapper;

    /**
     * 添加思维导图
     * @param xmind
     */
    @Override
    public void insertXmind(XmindVM xmind) {
        this.xmindMapper.insertXmind(xmind);
    }

    /**
     * 修改思维导图
     * @param xmind
     */
    @Override
    public void updateXmind(XmindVM xmind) {
        this.xmindMapper.updateXmind(xmind);
    }

    /**
     * 删除思维导图
     * @param ids
     */
    @Override
    public void deleteXmindByIds(List<Integer> ids) {
        this.xmindMapper.deleteXmindByIds(ids);
    }

    /**
     * 分页查询思维导图
     * @param xmind,requestVM
     * @param
     * @return
     */
    @Override
    public PageInfo<Xmind> queryXmind(XmindVM xmind) {
        return PageHelper.startPage(xmind.getPageIndex(),xmind.getPageSize(),"id desc").doSelectPageInfo(() ->
                xmindMapper.queryXmind(xmind)
        );
    }

    /**
     * 检查编码是否重复
     * @param  code
     * @return
     */
    @Override
    public Integer checkCode(String code) {
        Integer count=this.xmindMapper.checkXmindCode(code);
        return count;
    }
}
