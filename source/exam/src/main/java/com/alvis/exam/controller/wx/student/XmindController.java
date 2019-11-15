package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Xmind;
import com.alvis.exam.service.XmindService;
import com.alvis.exam.viewmodel.admin.user.XmindVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author yangsy
 */
@Api(value = "微信端user", tags = "用户api")
@RestController("WXXmindController")
@RequestMapping(value = "/api/wx/student/xmind")
@AllArgsConstructor
@ResponseBody
public class XmindController extends BaseApiController {
	
	@Autowired
    private XmindService xmindService;

    /**
     * 分页查询思维导图
     * @param xmind
     * @param
     * @return
     */
    @RequestMapping(value = "/queryXmind", method = RequestMethod.POST)
    public RestResponse<PageInfo<Xmind>> queryXmind(XmindVM xmind) {

        PageInfo<Xmind> xmindPageInfo = xmindService.queryXmind(xmind);
        return RestResponse.ok(xmindPageInfo);
    }

}
