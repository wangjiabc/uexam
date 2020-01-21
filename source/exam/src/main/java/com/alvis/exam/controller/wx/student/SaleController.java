package com.alvis.exam.controller.wx.student;


import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.dataSource.DataSourceSwitch;
import com.alvis.exam.domain.FxdhqkbFgsKhxxcx;
import com.alvis.exam.service.SaleService;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    public RestResponse saleBasic(MessageRequestVM messageRequestVM) {
        PageInfo<FxdhqkbFgsKhxxcx> list = saleService.findBasic(messageRequestVM);
        return RestResponse.ok(list);
    }


    /**
     * 销售数据详情
     */
    @RequestMapping(value = "details")
    public RestResponse saleDetails(Integer xh) {
        FxdhqkbFgsKhxxcx khxxcx = saleService.findDetails(xh);
        return RestResponse.ok(khxxcx);
    }

}
