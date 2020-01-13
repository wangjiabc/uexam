package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Xmind;
import com.alvis.exam.service.XmindService;
import com.alvis.exam.viewmodel.admin.user.XmindVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author yangsy
 */
@CrossOrigin
@RestController("AdminXmindController")
@RequestMapping(value = "/api/admin/xmind")
@AllArgsConstructor
public class XmindController extends BaseApiController {
	
	@Autowired
    private XmindService xmindService;

    /**
     * 添加思维导图
     * @param model
     * @return
     */
    @RequestMapping(value = "/insertXmind", method = RequestMethod.POST)
    public RestResponse<Xmind> insert(@RequestBody XmindVM model){

        this.xmindService.insertXmind(model);
        return RestResponse.ok();
    }

    /**
     * 修改思维导图
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatexmind", method = RequestMethod.POST)
    public RestResponse<Xmind> update(@RequestBody XmindVM model){

        this.xmindService.updateXmind(model);
        return RestResponse.ok();
    }

    /**
     * 删除思维导图
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteXmind", method = RequestMethod.POST)
    public RestResponse delete(@RequestBody XmindVM model) {

        this.xmindService.deleteXmindByIds(Arrays.asList(model.getId()));
        return RestResponse.ok();
    }

    /**
     * 分页查询思维导图
     * @param xmind
     * @param
     * @return
     */
    @RequestMapping(value = "/queryXmind", method = RequestMethod.POST)
    public RestResponse<PageInfo<Xmind>> queryXmind(@RequestBody XmindVM xmind) {

        PageInfo<Xmind> xmindPageInfo = xmindService.queryXmind(xmind);
        return RestResponse.ok(xmindPageInfo);
    }

}
