package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.configuration.property.SystemConfig;
import com.alvis.exam.controller.wx.BaseWXApiController;
import com.alvis.exam.domain.Message;
import com.alvis.exam.domain.MessageUser;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.UserEventLog;
import com.alvis.exam.domain.dto.Integral.IntegralBasic;
import com.alvis.exam.domain.dto.article.ExamDTO;
import com.alvis.exam.domain.dto.article.UserDTO;
import com.alvis.exam.domain.enums.RoleEnum;
import com.alvis.exam.domain.enums.UserStatusEnum;
import com.alvis.exam.event.UserEvent;
import com.alvis.exam.service.AuthenticationService;
import com.alvis.exam.service.MessageService;
import com.alvis.exam.service.UserEventLogService;
import com.alvis.exam.service.UserService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.utility.WxUtil;
import com.alvis.exam.viewmodel.student.user.*;
import com.alvis.exam.viewmodel.wx.student.user.QueryTimeVO;
import com.alvis.exam.viewmodel.wx.student.user.QueryUserScoreVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author alvis
 */
@CrossOrigin
@Api(value = "微信端user",tags = "用户api")
@Controller("WXStudentUserController")
@RequestMapping(value = "/api/wx/student/user")
@AllArgsConstructor
@ResponseBody
public class UserController extends BaseWXApiController {
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private UserEventLogService userEventLogService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/current", method = RequestMethod.POST)
    public RestResponse<UserResponseVM> current() {
        User user = userService.selectById(getCurrentUser().getId());

        UserResponseVM userVm = UserResponseVM.from(user);
        userVm.setBirthDay(DateTimeUtil.dateShortFormat(user.getBirthDay()));
        return RestResponse.ok(userVm);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResponse register(@Valid UserRegisterVM model) {
        User existUser = userService.getUserByUserName(model.getUserName());
        if (null != existUser) {
            return new RestResponse<>(2, "用户已存在");
        }

        String code = model.getCode();
        String openid = WxUtil.getOpenId(systemConfig.getWx().getAppid(), systemConfig.getWx().getSecret(), code);
        if (null == openid) {
            return RestResponse.fail(4, "获取微信OpenId失败");
        }

        User user = modelMapper.map(model, User.class);
        user.setPhone(model.getPhone());
        user.setUserName(model.getUserName());
        user.setAge(model.getAge());
        user.setSex(model.getSex());

//        String encodePwd = authenticationService.pwdEncode(model.getPassword());
        user.setUserUuid(UUID.randomUUID().toString());
//        user.setPassword(encodePwd);
        user.setRole(RoleEnum.STUDENT.getCode());
        user.setStatus(UserStatusEnum.Disable.getCode());   //默认不能登录  必须管理员给与权限
        user.setLastActiveTime(new Date());
        user.setCreateTime(new Date());
        user.setDeleted(false);
        user.setWxOpenId(openid);
        user.setImagePath(model.getImagePath());    //传入头像

        userService.insertByFilter(user);
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        userEventLog.setContent("欢迎 " + user.getUserName() + " 注册来到系统");
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        return RestResponse.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RestResponse<UserResponseVM> update(@Valid UserUpdateVM model) {
        if (StringUtils.isBlank(model.getBirthDay())) {
            model.setBirthDay(null);
        }
        User user = userService.selectById(getCurrentUser().getId());
        modelMapper.map(model, user);
        user.setModifyTime(new Date());
        userService.updateByIdFilter(user);
        UserEventLog userEventLog = new UserEventLog(user.getId(), user.getUserName(), user.getRealName(), new Date());
        userEventLog.setContent(user.getUserName() + " 更新了个人资料");
        eventPublisher.publishEvent(new UserEvent(userEventLog));
        UserResponseVM userVm = UserResponseVM.from(user);
        return RestResponse.ok(userVm);
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public RestResponse<List<UserEventLogVM>> log() {
        User user = getCurrentUser();
        List<UserEventLog> userEventLogs = userEventLogService.getUserEventLogByUserId(user.getId());
        List<UserEventLogVM> userEventLogVMS = userEventLogs.stream().map(d -> {
            UserEventLogVM vm = modelMapper.map(d, UserEventLogVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(d.getCreateTime()));
            return vm;
        }).collect(Collectors.toList());
        return RestResponse.ok(userEventLogVMS);
    }

    @RequestMapping(value = "/message/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<MessageResponseVM>> messagePageList(MessageRequestVM messageRequestVM) {
        messageRequestVM.setReceiveUserId(getCurrentUser().getId());
        PageInfo<MessageUser> messageUserPageInfo = messageService.studentPage(messageRequestVM);
        List<Integer> ids = messageUserPageInfo.getList().stream().map(d -> d.getMessageId()).collect(Collectors.toList());
        List<Message> messages = ids.size() != 0 ? messageService.selectMessageByIds(ids) : null;
        PageInfo<MessageResponseVM> page = PageInfoHelper.copyMap(messageUserPageInfo, e -> {
            MessageResponseVM vm = modelMapper.map(e, MessageResponseVM.class);
            messages.stream().filter(d -> e.getMessageId().equals(d.getId())).findFirst().ifPresent(message -> {
                vm.setTitle(message.getTitle());
                vm.setContent(message.getContent());
                vm.setSendUserName(message.getSendUserName());
            });
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/message/detail/{id}", method = RequestMethod.POST)
    public RestResponse messageDetail(@PathVariable Integer id) {
        Message message = messageService.messageDetail(id);
        return RestResponse.ok(message);
    }


    @RequestMapping(value = "/message/unreadCount", method = RequestMethod.POST)
    public RestResponse unReadCount() {
        Integer count = messageService.unReadCount(getCurrentUser().getId());
        return RestResponse.ok(count);
    }

    @RequestMapping(value = "/message/read/{id}", method = RequestMethod.POST)
    public RestResponse read(@PathVariable Integer id) {
        messageService.read(id);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/calculateUserScore", method = RequestMethod.POST)
    public RestResponse calculateUserScore(@RequestBody QueryUserScoreVO queryUserScoreVO) {
        return RestResponse.ok(userService.calculateUserScore(queryUserScoreVO.getUserId() == null ? getCurrentUser().getId() : queryUserScoreVO.getUserId(), queryUserScoreVO.getStartTime(), queryUserScoreVO.getEndTime()));
    }

    @ApiOperation(value="测试方法,计算用户积分排名", produces = "application/json; charset=utf-8")
    @RequestMapping(value = "/calculateUsersScore", method = RequestMethod.POST)
    public RestResponse calculateUsersScore(@RequestBody QueryUserScoreVO queryUserScoreVO) {
        return RestResponse.ok(userService.calculateUsersScore(queryUserScoreVO.getStartTime(), queryUserScoreVO.getEndTime()));
    }

    /**
     * 阅读积分排名
     * @param queryTimeVO   时间区间
     * @param requestVM     分页对象
     * @return
     */
    @RequestMapping(value = "selectUserRanking", method = RequestMethod.POST)
    public RestResponse<PageInfo<UserDTO>> selectUserRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM) {
        requestVM.setReceiveUserId(getCurrentUser().getId());
        queryTimeVO.setEndTime(DateTimeUtil.addDuration(queryTimeVO.getEndTime(), Duration.ofDays(1)));
        PageInfo<UserDTO> userDtoPageInfo = userService.selectUserRanking(queryTimeVO, requestVM);
        //排名
        for (int i = 1, rank = userDtoPageInfo.getStartRow(); i <= userDtoPageInfo.getList().size(); i++) {
            UserDTO userDTO = userDtoPageInfo.getList().get(i - 1);
            userDTO.setRank(rank++);
        }
        return RestResponse.ok(userDtoPageInfo);
    }


    /**
     * 个人阅读基础情况
     * @return
     */
    @RequestMapping(value = "userReadBasic", method = RequestMethod.POST)
    public RestResponse userReadBasic() {
        Integer id = getCurrentUser().getId();
        IntegralBasic list = userService.userReadBasic(id);
        return RestResponse.ok(list);
    }


    /**
     * 考试积分排名
     * @param queryTimeVO   时间区间
     * @param requestVM     分页对象
     * @return
     */
    @RequestMapping(value = "selectExamRanking", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamDTO>> selectExamRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM) {
        requestVM.setReceiveUserId(getCurrentUser().getId());
        queryTimeVO.setEndTime(DateTimeUtil.addDuration(queryTimeVO.getEndTime(), Duration.ofDays(1)));
        PageInfo<ExamDTO> examDTOPageInfo = userService.selectExamRanking(queryTimeVO, requestVM);
        List<ExamDTO> list = examDTOPageInfo.getList();
        for (ExamDTO examDTO : list) {
            Integer userScore = examDTO.getUserScore();
            if(userScore != 0){
                userScore = userScore / 10;
                examDTO.setUserScore(userScore);
            }
        }
        //排名
        for (int i = 1, rank = examDTOPageInfo.getStartRow(); i <= examDTOPageInfo.getList().size(); i++) {
            ExamDTO examDTO = examDTOPageInfo.getList().get(i - 1);
            examDTO.setRank(rank++);
        }

        return RestResponse.ok(examDTOPageInfo);
    }

    /**
     * 个人考试基础情况
     * @return
     */
    @RequestMapping(value = "userExamBasic", method = RequestMethod.POST)
    public RestResponse userExamBasic() {
        Integer id = getCurrentUser().getId();
        IntegralBasic list = userService.userExamBasic(id);
        return RestResponse.ok(list);
    }



    /**
     * 修改个人资料
     */
    @RequestMapping(value = "updateUser")
    public  RestResponse updateUser(User user){
        Integer id = getCurrentUser().getId();
        user.setId(id);
        userService.updateUser(user);
        return RestResponse.ok();
    }

    /**
     * 返回登录权限状态：1为禁用，2为启用
     */
    @RequestMapping(value = "returnState")
    public  Integer returnState(){
        Integer id = getCurrentUser().getId();
        User user = userService.selectById(id);
        Integer status = user.getStatus();
        return status;
    }
}
