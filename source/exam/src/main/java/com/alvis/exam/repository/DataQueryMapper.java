package com.alvis.exam.repository;

import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.WideNarrow;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *  数据查询
 * @author yangsy
 */

@Mapper
public interface DataQueryMapper extends BaseMapper<SourcePut> {


    /**
     * 根据openId查询用户uuid
     */
    @Select("select * from t_user where wx_open_id='${wxOpenId}'")
    User queryuser(@Param("wxOpenId") String wxOpenId);

    /**
     * 分页查询本轮货源投放
     * @param sourcePut
     */
    @Select("select * from source_put")
    List<SourcePut> querySourcePut(SourcePutVM sourcePut);

    /**
     * 根据个人ID 查询本月完成进度
     */
    @Select("SELECT a.real_sale,a.date,b.user_name,b.month_sale_norm,(b.month_sale_norm/a.real_sale)*100 as completionRate FROM t_sale_data a ,t_user b where a.users_id=b.user_uuid and month_sale_norm >0 and a.users_id='${uuid}'")
    List<Map<String,Object>> queryCompletionSchedule(@Param("uuid") String uuid);

    /**
     * 根据个人ID 查询本月完成总销
     */
    @Select("select a.real_sale from t_sale_data a where a.users_id='${uuid}'")
    SaleData queryTotalSale(@Param("uuid") String uuid);

    /**
     * 本月完成进度宽窄
     */
    @Select("select a.* from wide_narrow a ,t_user b where a.user_id=b.user_uuid and a.user_id='${userid}'")
    WideNarrow queryWideNarrow(@Param("userid") String userid);

    /**
     * 阶段性考核指标
     */
}
