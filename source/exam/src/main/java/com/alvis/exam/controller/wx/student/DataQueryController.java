package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.WideNarrow;
import com.alvis.exam.service.DataQueryService;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yangsy
 */
@CrossOrigin
@RestController("AdminDataQueryController")
@RequestMapping(value = "/api/admin/dataQuery")
@AllArgsConstructor
public class DataQueryController extends BaseApiController {
	
	@Autowired
    private DataQueryService dataQueryService;

    /**
     * 分页查询本轮货源投放
     * @param sourcePut
     * @param
     * @return
     */
    @RequestMapping(value = "/querySourcePut", method = RequestMethod.POST)
    public RestResponse<PageInfo<SourcePut>> querySourcePut(@RequestBody SourcePutVM sourcePut) {

        PageInfo<SourcePut> sourcePutPageInfo = dataQueryService.querySourcePut(sourcePut);
        return RestResponse.ok(sourcePutPageInfo);
    }

    /**
     * 根据个人ID 查询本月完成进度
     */
    @RequestMapping(value = "/queryCompletionSchedule", method = RequestMethod.POST)
    public RestResponse  queryCompletionSchedule(){
        String wxOpenId = getCurrentUser().getWxOpenId();
        User user=this.dataQueryService.queryuser(wxOpenId);
        List<Map<String,Object>>  list= this.dataQueryService.queryCompletionSchedule(user.getUserUuid());

        return RestResponse.ok(list);
    }

    /**
     * 本月完成进度总销
     */
    @RequestMapping(value = "/queryTotalSale", method = RequestMethod.POST)
    public RestResponse queryTotalSale(){
        String wxOpenId = getCurrentUser().getWxOpenId();
        User user=this.dataQueryService.queryuser(wxOpenId);
        SaleData saleData=this.dataQueryService.queryTotalSale(user.getUserUuid());
        return RestResponse.ok(saleData);
    }

    /**
     * 本月完成进度宽窄
     */
    @RequestMapping(value = "/queryWideNarrow", method = RequestMethod.POST)
    public RestResponse queryWideNarrow(){
        String wxOpenId = getCurrentUser().getWxOpenId();
        User user=this.dataQueryService.queryuser(wxOpenId);
        WideNarrow wideNarrow=this.dataQueryService.queryWideNarrow(user.getUserUuid());
        return RestResponse.ok(wideNarrow);
    }

    /**
     * 阶段性考核指标
     */

}