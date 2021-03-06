package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.SaleData;
import com.alvis.exam.domain.SourcePut;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.WideNarrow;
import com.alvis.exam.service.DataQueryService;
import com.alvis.exam.viewmodel.admin.user.SourcePutVM;
import com.alvis.exam.viewmodel.admin.user.UserVM;
import com.alvis.exam.viewmodel.admin.user.WideNarrowVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author yangsy
 */
@CrossOrigin
@Controller("WXDataQueryController")
@RequestMapping(value = "/api/wx/student/dataQuery")
@AllArgsConstructor
@ResponseBody
public class DataQueryController extends BaseWXApiController {
	
	@Autowired
    private DataQueryService dataQueryService;

    /**
     * 分页查询本轮货源投放
     * @param sourcePut
     * @param
     * @return
     */
    @RequestMapping(value = "/querySourcePut", method = RequestMethod.POST)
    public RestResponse<PageInfo<SourcePut>> querySourcePut(SourcePutVM sourcePut) {

        PageInfo<SourcePut> sourcePutPageInfo = dataQueryService.querySourcePut(sourcePut);
        return RestResponse.ok(sourcePutPageInfo);
    }

    /**
     * 根据个人ID 查询本月完成进度
     */
    @RequestMapping(value = "/queryCompletionSchedule", method = RequestMethod.POST)
    public RestResponse  queryCompletionSchedule(String uuid){
        String userid=uuid;
        List<Map<String,Object>>  list=null;
         if(userid==null){
             String wxOpenId = getCurrentUser().getWxOpenId();
             User user=this.dataQueryService.queryuser(wxOpenId);
             list= this.dataQueryService.queryCompletionSchedule(user.getUserUuid());
         }else{
             list= this.dataQueryService.queryCompletionSchedule(userid);
         }


        return RestResponse.ok(list);
    }

    /**
     * 本月完成进度总销
     */
    @RequestMapping(value = "/queryTotalSale", method = RequestMethod.POST)
    public RestResponse queryTotalSale(String uuid){
        String userid=uuid;
        SaleData saleData=null;
        if(userid==null){
            String wxOpenId = getCurrentUser().getWxOpenId();
            User user=this.dataQueryService.queryuser(wxOpenId);
            saleData=this.dataQueryService.queryTotalSale(user.getUserUuid());
        }else{
            saleData=this.dataQueryService.queryTotalSale(userid);
        }

        return RestResponse.ok(saleData);
    }

    /**
     * 本月完成进度宽窄
     */
    @RequestMapping(value = "/queryWideNarrow", method = RequestMethod.POST)
    public RestResponse queryWideNarrow(String uuid){
        String userid=uuid;
        WideNarrow wideNarrow=null;
        if(userid==null){
            String wxOpenId = getCurrentUser().getWxOpenId();
            User user=this.dataQueryService.queryuser(wxOpenId);
            wideNarrow=this.dataQueryService.queryWideNarrow(user.getUserUuid());
        }else{
             wideNarrow=this.dataQueryService.queryWideNarrow(userid);
        }
        return RestResponse.ok(wideNarrow);
    }

    /**
     * 本月完成进度宽窄（管理员）
     */
    @RequestMapping(value = "/queryWideNarrowMag", method = RequestMethod.POST)
    public RestResponse<PageInfo<WideNarrow>> queryWideNarrowMag(WideNarrowVM wideNarrow) {

        PageInfo<WideNarrow> wideNarrowPageInfo = dataQueryService.queryWideNarrowMag(wideNarrow);
        return RestResponse.ok(wideNarrowPageInfo);
    }

    /**
     * 阶段性考核指标完成率
     */
    @RequestMapping(value = "/userQuery", method = RequestMethod.POST)
    public RestResponse userQuery(String uuid){
        String userid=uuid;
        List<Map<String,Object>>  list=null;
        if(userid==null){
            String wxOpenId = getCurrentUser().getWxOpenId();
            User user=this.dataQueryService.queryuser(wxOpenId);
            list= this.dataQueryService.queryCompletionStage(user.getUserUuid());
        }else{
            list= this.dataQueryService.queryCompletionStage(userid);
        }
        return RestResponse.ok(list);
    }

    /**
     * 阶段性考核指标（管理员）
     */
    @RequestMapping(value = "/queryUserMag", method = RequestMethod.POST)
    public RestResponse<PageInfo<User>> queryUserMag(UserVM user) {

        PageInfo<User> userPageInfo = dataQueryService.queryuserMge(user);
        return RestResponse.ok(userPageInfo);
    }

    /**
     * 查询本月完成进度(管理员)
     */
    @RequestMapping(value = "/queryCompletionScheduleMGE", method = RequestMethod.POST)
    public RestResponse queryCompletionScheduleMGE(String uuid){
        String userid=uuid;
        List<Map<String,Object>> list=null;
        if(userid==null){
            String wxOpenId = getCurrentUser().getWxOpenId();
            User user=this.dataQueryService.queryuser(wxOpenId);
             list= this.dataQueryService.queryCompletionScheduleMGE(user.getUserUuid());

        }else{
            list= this.dataQueryService.queryCompletionScheduleMGE(userid);
        }
        return RestResponse.ok(list);
    }

    /**
     * 查询阶段完成进度（管理员）
     */
    @RequestMapping(value = "/queryCompletionStageMGE", method = RequestMethod.POST)
    public RestResponse queryCompletionStageMGE(String uuid){
        String userid=uuid;
        List<Map<String,Object>> list=null;
        if(userid==null){
            String wxOpenId = getCurrentUser().getWxOpenId();
            User user=this.dataQueryService.queryuser(wxOpenId);
            list= this.dataQueryService.queryCompletionStageMGE(user.getUserUuid());
        }else{
            list= this.dataQueryService.queryCompletionStageMGE(userid);
        }
        return RestResponse.ok(list);
    }

}
