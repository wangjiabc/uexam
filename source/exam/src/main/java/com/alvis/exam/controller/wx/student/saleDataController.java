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
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/queryCashregister", method = RequestMethod.POST)
    public RestResponse queryCashregister() {
        List<cashregister> cashregisterPageInfo = saleDataService.queryCashregister();

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

    //////////////////////给前端提供参数//////////////////////////////


    /**
     * 宽窄系列指标(返回出参数客户经理)
     */
    @RequestMapping(value = "/queryBrankManager", method = RequestMethod.POST)
    public RestResponse queryBrankManager(){
        List<String> data=saleDataService.queryBrankManager();

        return RestResponse.ok(data);
    }

    /**
     * 宽窄系列指标(返回类型)
     */
    @RequestMapping(value = "/queryBrankType", method = RequestMethod.POST)
    public RestResponse queryBrankType(){
        List<String> data=saleDataService.queryBrankType();

        return RestResponse.ok(data);
    }

    /**
     * 本月完成进度(返回出参数客户经理)
     */
    @RequestMapping(value = "/queryProgressManager", method = RequestMethod.POST)
    public RestResponse queryProgressManager(){
        List<String> data=saleDataService.queryProgressManager();

        return RestResponse.ok(data);
    }

    /**
     * 分页查询本轮货源投放明细(返回出参数客户经理)
     */
    @RequestMapping(value = "/querySourceManager", method = RequestMethod.POST)
    public RestResponse querySourceManager(){
        List<String> data=saleDataService.querySourceManager();

        return RestResponse.ok(data);
    }

    /**
     * 扫码进度(返回出参数客户经理)
     */
    @RequestMapping(value = "/querycodeManager", method = RequestMethod.POST)
    public RestResponse querycodeManager(){
        List<String> data=saleDataService.querycodeManager();

        return RestResponse.ok(data);
    }

    /**
     * 红码管家（传客户经理和状态和手机订烟）
     */
    @RequestMapping(value = "/querylogManager", method = RequestMethod.POST)
    public RestResponse querylogManager(){
        List<String> data=saleDataService.querylogManager();

        return RestResponse.ok(data);
    }

}
