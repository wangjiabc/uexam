package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.service.DataQueryService;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "/queryXmind", method = RequestMethod.POST)
    public RestResponse<PageInfo<SourcePut>> querySourcePut(@RequestBody SourcePutVM sourcePut) {

        PageInfo<SourcePut> sourcePutPageInfo = dataQueryService.querySourcePut(sourcePut);
        return RestResponse.ok(sourcePutPageInfo);
    }

    /**
     * 根据个人ID 查询本月完成进度
     */
    @RequestMapping(value = "/queryXmind", method = RequestMethod.POST)
    public RestResponse  queryCompletionSchedule(String uuid){
        List<String>  list= this.dataQueryService.queryCompletionSchedule(uuid);

        return RestResponse.ok(list);
    }

    /**
     * 本月完成进度总销
     */
    @RequestMapping(value = "/queryXmind", method = RequestMethod.POST)
    public RestResponse queryTotalSale(String uuid){
        SaleData saleData=this.dataQueryService.queryTotalSale(uuid);
        return RestResponse.ok(saleData);
    }


    /**
     * 本月完成进度宽窄
     */

    /**
     * 阶段性考核指标
     */

}
