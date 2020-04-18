package com.alvis.exam.repository;

import com.alvis.exam.domain.*;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    List<Source> querySource(Source source);

    /**
     * 查询宽窄系列指标
     */
    @Select("select * from brand where manager=#{manager} and type=#{type}")
    brank queryBrank(@Param("manager") String manager, @Param("type") String type);

    /**
     * 查询扫码进度
     */
    @Select("SELECT * FROM sweepcode where manager=#{manager}")
    sweepcode querySweepcode(@Param("manager") String manager);

    /**
     * 智慧收银机进度
     * @param cashregist
     */
    @Select("select * from cashregister")
    List<cashregister> queryCashregister(cashregister cashregist);

    /**
     * 本月完成进度
     */
    @Select("select * from saleprogress where manager=#{manager} and month=#{month}")
    saleProgress querysaleProgress(@Param("manager") String manager,@Param("month") String month);

    /**
     * 红码管家
     */
    @Select("select * from logSituation where phoneOrder=#{phoneOrder} and status=#{status} and customerManager=#{customerManager}")
    List<logSituation> querylogSituation(String customerManager, String status, String phoneOrder);

    //////////////////////给前端提供参数//////////////////////////////

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    @Select("select manager from brand")
    List<String> queryBrankManager();

    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    @Select("select distinct  type from brand")
    List<String> queryBrankType();

    /**
     * 本月完成进度(返回出参数客户经理)
     */
    @Select("select manager from saleprogress")
    List<String> queryProgressManager();

}
