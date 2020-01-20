package com.alvis.exam.configuration.spring.security;

import com.alvis.exam.base.SystemCode;
import com.alvis.exam.context.WebContext;
import com.alvis.exam.domain.UserEventLog;
import com.alvis.exam.event.UserEvent;
import com.alvis.exam.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登出
 *
 * @author alvis
 */
@Component
@AllArgsConstructor
public class RestLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {
//public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
    private  ApplicationEventPublisher eventPublisher;
	@Autowired
	private  UserService userService;
    @Autowired
    private  WebContext webContext;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        webContext.getCurrentUser();
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        com.alvis.exam.domain.User user = userService.getUserByUserName(springUser.getUsername());
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        userEventLog.setContent(user.getUserName() + " 登出了烟草局学习平台");
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        RestUtil.response(response, SystemCode.OK);
    }
}
