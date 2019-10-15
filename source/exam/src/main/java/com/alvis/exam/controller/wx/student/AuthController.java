package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.SystemConfig;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.UserToken;
import com.alvis.exam.domain.enums.UserStatusEnum;
import com.alvis.exam.service.AuthenticationService;
import com.alvis.exam.service.UserService;
import com.alvis.exam.service.UserTokenService;
import com.alvis.exam.utility.WxUtil;
import com.alvis.exam.viewmodel.wx.student.user.BindInfo;
import com.voucher.manage.tools.MyTestUtil;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Controller("WXStudentAuthController")
@RequestMapping(value = "/api/wx/student/auth")
@AllArgsConstructor
@ResponseBody
public class AuthController extends BaseWXApiController {
	@Autowired
    private  SystemConfig systemConfig;
	@Autowired
	private  AuthenticationService authenticationService;
	@Autowired
	private  UserService userService;
	@Autowired
	private  UserTokenService userTokenService;

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public RestResponse bind(@Valid BindInfo model) {
    	//登录
        String code = model.getCode();
        String openid = WxUtil.getOpenId(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == openid) {
            return RestResponse.fail(4, "获取微信OpenId失败");
        }
        com.alvis.exam.domain.User user = userService.selectByWxOpenId(openid);
        //com.alvis.exam.domain.User user = userService.getUserByUserName(model.getUserName());
        if (user == null) {
            return RestResponse.fail(5, "用户未绑定微信,请注册");
        }
        UserToken userToken = userTokenService.bind(user);
        return RestResponse.ok(userToken.getToken());
    }


    @RequestMapping(value = "/checkBind", method = RequestMethod.POST)
    public RestResponse checkBind(@Valid @NotBlank String code) {
    	System.out.println("code="+code);
        String openid = WxUtil.getOpenId(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == openid) {
            return RestResponse.fail(3, "获取微信OpenId失败");
        }
        UserToken userToken = userTokenService.checkBind(openid);
        if (null != userToken) {
            return RestResponse.ok(userToken.getToken());
        }
        return RestResponse.fail(2, "用户未绑定");
       // UserToken userToken2=new UserToken();
      //  userToken2.setWxOpenId(openid);
      //  return RestResponse.ok(userToken);
    }


    @RequestMapping(value = "/unBind", method = RequestMethod.POST)
    public RestResponse unBind() {
        UserToken userToken = getUserToken();
        userTokenService.unBind(userToken);
        return RestResponse.ok();
    }
}
