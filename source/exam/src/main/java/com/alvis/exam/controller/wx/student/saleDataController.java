package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.*;
import com.alvis.exam.service.saleDataService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yangsy
 */
@CrossOrigin
@Controller("WXsaleDataController")
@RequestMapping(value = "/api/wx/student/saleData")
@AllArgsConstructor
@ResponseBody
public class saleDataController extends BaseWXApiController {

	@Autowired
    private saleDataService saleDataService;


    /**
     * 分页查询本轮货源投放明细（传分页参数和客户经理）
     * @param source
     * @param
     * @return
     */
    @RequestMapping(value = "/SourcePut", method = RequestMethod.POST)
    public RestResponse<PageInfo<Source>> SourcePut(Source source) {
        PageInfo<Source> sourcePageInfo = saleDataService.querySource(source);

        return RestResponse.ok(sourcePageInfo);
    }

    /**
     * 宽窄系列指标
     */
    @RequestMapping(value = "/queryBrank", method = RequestMethod.POST)
    public RestResponse queryBrank(String manager,String type){
        brank  data=saleDataService.queryBrank(manager,type);

        return RestResponse.ok(data);
    }

    /**
     * 扫码进度
     */
    @RequestMapping(value = "/querySweepcode", method = RequestMethod.POST)
    public RestResponse querySweepcode(String manager){
        sweepcode data=saleDataService.querySweepcode(manager);

        return RestResponse.ok(data);
    }

    /**
     * 智慧收银机进度（传分页参数）
     * @param cashregist
     * @param
     * @return
     */
    @RequestMapping(value = "/queryCashregister", method = RequestMethod.POST)
    public RestResponse<PageInfo<cashregister>> queryCashregister(cashregister cashregist) {
        PageInfo<cashregister> cashregisterPageInfo = saleDataService.queryCashregister(cashregist);

        return RestResponse.ok(cashregisterPageInfo);
    }


    /**
     * 本月完成进度（传客户经理和月份）
     * @param manager
     * @param month
     * @return
     */
    @RequestMapping(value = "/querysaleProgress", method = RequestMethod.POST)
    public RestResponse querysaleProgress(String manager,String month) {
        saleProgress data = saleDataService.querysaleProgress(manager, month);

        return RestResponse.ok(data);
    }

    /**
     * 红码管家（传客户经理和状态和手机订烟）
     * @param customerManager
     * @param status
     * @param phoneOrde
     * @return
     */
    @RequestMapping(value = "/querylogSituation", method = RequestMethod.POST)
    public RestResponse querylogSituation (String customerManager, String status, String phoneOrde) {
        List<logSituation> data=saleDataService.querylogSituation(customerManager,status,phoneOrde);

        return RestResponse.ok(data);
    }

}
