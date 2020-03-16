package com.alvis.exam.controller.wx.student;


import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.dataSource.DataSourceSwitch;
import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.domain.fxdhqkbFgsPqxsjdb;
import com.alvis.exam.service.SaleService;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.alvis.exam.viewmodel.wx.student.KeyObjDTO;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping(value = "/api/wx/student/sale")
@AllArgsConstructor
@ResponseBody
public class SaleController {

    @Resource
    private SaleService saleService;

    /**
     * 销售数据列表
     */
    @RequestMapping(value = "basic")
    public RestResponse saleBasic(MessageRequestVM messageRequestVM,String startDate1,String endDate1,String khjl1) throws ParseException {
        DataSourceSwitch.switchDataSource();
//        PageInfo<FxdhqkbFgsKhxxcx> list = saleService.findBasic(messageRequestVM);
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat slf1 = new SimpleDateFormat("yyyy-MM");
        //拿到今年今月
        String format = slf1.format(new Date());
        if("当月".equals(startDate1) || "当月".equals(endDate1)){
            startDate1 = format + "-01";
            endDate1 = format + "-30";
        }
        Date parse1 = slf.parse(startDate1);
        Date parse2 = slf.parse(endDate1);
        messageRequestVM.setStartDate(parse1);
        messageRequestVM.setEndDate(parse2);

        String khjl = null;
        if("-1".equals(khjl1)){
            khjl1 = null;
        }
        if(khjl1 != null){
            List<String> manager = saleService.findManager();
            khjl = manager.get(Integer.parseInt(khjl1));
        }

        messageRequestVM.setKhjl(khjl);

        Double JE = saleService.findDYJE1();
        Integer count = saleService.findCount1();

        //格式化金额
        String format1 = new DecimalFormat("#.00").format(JE);
        double v = Double.parseDouble(format1);

        Map<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("money",v);


        PageInfo<fxdhqkbFgsPqxsjdb> pageInfo = saleService.findB2(messageRequestVM);
        List<fxdhqkbFgsPqxsjdb> list = pageInfo.getList();
        for (fxdhqkbFgsPqxsjdb fxdhqkbFgsPqxsjdb : list) {
            fxdhqkbFgsPqxsjdb.setStartDate(parse1);
            fxdhqkbFgsPqxsjdb.setEndDate(parse2);
            fxdhqkbFgsPqxsjdb.setMap(map);
        }
        return RestResponse.ok(pageInfo);
    }


    /**
     * 销售数据详情
     */
    @RequestMapping(value = "details")
    public RestResponse saleDetails(String khbh) {
        DataSourceSwitch.switchDataSource();
//        FxdhqkbFgsKhxxcx khxxcx = saleService.findDetails(xh);
        fxdhqkbFgsPqxsjdb khxxcx = saleService.findD2(khbh);
        return RestResponse.ok(khxxcx);
    }

    /**
     * 客户经理
     */
    @RequestMapping(value = "manager")
    public RestResponse manager() {
        DataSourceSwitch.switchDataSource();
        List<String> manager = saleService.findManager();
        return RestResponse.ok(manager);
    }

    /**
     * 当月销售金额   //未做当月筛选
     */
    @RequestMapping(value = "money")
    public RestResponse money(String khjl) {
        DataSourceSwitch.switchDataSource();
        List<String> manager = saleService.findManager();
        String s = manager.get(Integer.parseInt(khjl));

        Double JE = saleService.findDYJE(s);
        Integer count = saleService.findCount(s);

        //格式化金额
        String format = new DecimalFormat("#.00").format(JE);
        double v = Double.parseDouble(format);

        Map<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("money",v);

        return RestResponse.ok(map);
    }

}
