package com.alvis.exam.repository;

import com.alvis.exam.domain.Xmind;
import com.alvis.exam.viewmodel.admin.user.XmindVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 思维导图
 * @author yangsy
 */

@Mapper
public interface XmindMapper extends BaseMapper<Xmind> {

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
     * @param ids ids
     */
    void deleteXmindByIds(List<Integer> ids);

    /**
     * 查询思维导图
     * @param xmind
     */
    List<Xmind> queryXmind(XmindVM xmind);

    /**
     * 检查编码是否重复
     * @param  code
     */
    Integer checkXmindCode(String code);
}
