package com.alvis.exam.repository;

import com.alvis.exam.domain.*;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *  数据查询
 * @author yangsy
 */

@Mapper
public interface saleDataMapper extends BaseMapper<SourcePut> {

    /**
     * 分页查询本轮货源投放
     * @param source
     */
    PageInfo<Source> querySource(Source source);

    /**
     * 查询宽窄系列指标
     */
    @Select("select * from brand where manager='#{manager}' and type='#{type}'")
    brank queryBrank(@Param("manager") String manager, @Param("type") String type);

    /**
     * 查询扫码进度
     */
    @Select("SELECT * FROM sweepcode where manager='#{manager}'")
    sweepcode querySweepcode(@Param("manager") String manager);

    /**
     * 智慧收银机进度
     * @param cashregist
     */
    @Select("select * from cashregister")
    PageInfo<cashregister> queryCashregister(cashregister cashregist);

}
