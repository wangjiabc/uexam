package com.alvis.exam.service;

import com.alvis.exam.domain.Xmind;
import com.alvis.exam.viewmodel.admin.user.XmindVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangsy
 */
public interface XmindService{
    /**
     * insertXmind
     *添加思维导图
     * @param xmind
     */
    void insertXmind(XmindVM xmind);

    /**
     * updateXmind
     *修改思维导图
     * @param xmind
     */
    void updateXmind(XmindVM xmind);

    /**
     * deleteXmindByIds
     *删除思维导图
     * @param ids
     */
    void deleteXmindByIds(List<Integer> ids);

    /**
     * selectXmind
     * 分页查询思维导图
     * @param xmind
     */
    PageInfo<Xmind> queryXmind(XmindVM xmind);

    /**
     * checkCode
     * 检查编码是否重复
     * @param  code
     */
    Integer checkCode(String code);
}
