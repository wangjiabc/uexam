package com.alvis.exam.controller.wx.student;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.context.WxContext;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.Users;
import com.alvis.exam.domain.VisitedUsers;
import com.alvis.exam.domain.dto.VisitUsersDTO;
import com.alvis.exam.repository.UserMapper;
import com.alvis.exam.repository.UsersMapper;
import com.alvis.exam.service.VisitUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping(value = "/api/wx/student/visit" )
@AllArgsConstructor
@ResponseBody
public class VisitUsersController {

    @Resource
    private VisitUserService visitUserService;
    @Resource
    private UserMapper userMapper;

    /**
     * 根据usersId查询信息
     */
    @RequestMapping(value = "findCon", method = RequestMethod.POST)
    public RestResponse findCon(Integer usersId) {
        Users users = visitUserService.findCon(usersId);
        return RestResponse.ok(users);
    }


    /**
     * users的部分信息
     * @return
     */
    @RequestMapping(value = "userCon", method = RequestMethod.POST)
    public RestResponse userCon() {
        List<VisitUsersDTO> list = visitUserService.findVisits();
        for (VisitUsersDTO visitUsersDTO : list) {
            visitUsersDTO.setId(visitUsersDTO.getUsersId());
        }
        return RestResponse.ok(list);
    }

    @Autowired
    private WxContext wxContext;

    /**
     * 拜访开始
     * @return
     */
    @RequestMapping(value = "visitStart", method = RequestMethod.POST)
    public RestResponse visitStart(VisitUsersDTO visitUsersDTO) {

        VisitedUsers visitedUsers = new VisitedUsers();
        visitedUsers.setUsersId(visitUsersDTO.getUsersId());        //被拜访人id
        visitedUsers.setVisitStartDate(new Date());                 //开始拜访时间

//        visitedUsers.setPositionId(visitUsersDTO.getPositionId());  //位置信息
//        visitedUsers.setLicense(visitUsersDTO.getLicense());        //被拜访人证号

        Integer id = wxContext.getCurrentUser().getId();
        visitedUsers.setUserId(id); //操作人id
        VisitedUsers visitedUsers1 = visitUserService.findMaxTime(visitedUsers);
        if(visitedUsers1 != null){
            visitedUsers.setTime(visitedUsers1.getTime() + 1);
        }
        else {
            visitedUsers.setTime(1);    //被拜访人第一次被该人访问
        }
        //存被拜访人信息
        visitUserService.saveStartDate(visitedUsers);
        return RestResponse.ok();
    }

    /**
     * 拜访结束
     * @return
     */
    @RequestMapping(value = "visitEnd", method = RequestMethod.POST)
    public RestResponse visitEnd(VisitUsersDTO visitUsersDTO) {

        VisitedUsers visitedUsers = new VisitedUsers();
        visitedUsers.setVisitEndDate(new Date());            //结束拜访时间
        visitedUsers.setUsersId(visitUsersDTO.getUsersId());        //被拜访人id
        visitedUsers.setUserId(wxContext.getCurrentUser().getId()); //操作人id
        //查询被拜访人在visitedUsers中的id
        VisitedUsers visitedUsers1 = visitUserService.findMaxTime(visitedUsers);
        Integer id = visitedUsers1.getId();
        visitedUsers.setId(id);

        //存被拜访人结束拜访时间
        visitUserService.saveEndDate(visitedUsers); //实则修改
        return RestResponse.ok();
    }

    //查看所有拜访人
    @RequestMapping(value = "findUser")
    public RestResponse findUser() {
        Integer id = wxContext.getCurrentUser().getId();
        User user = userMapper.getUserById(id);
        Integer wxRole = user.getWxRole();
        if(wxRole != 1){
            return RestResponse.fail(500,"无访问权限");
        }
        else {
            List<User> list = visitUserService.findUser();
            return RestResponse.ok(list);
        }
    }

    /**
     * 根据拜访人查看他的行动路线
     * @return
     */
    @RequestMapping(value = "visitUserSim", method = RequestMethod.POST)
    public RestResponse visitUserSim(String startDate,String endDate,User user) throws ParseException {
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate1 = slf.parse(startDate);
        Date endDate1 = slf.parse(endDate);
//        Integer userId = wxContext.getCurrentUser().getId();
        VisitedUsers visitedUsers = new VisitedUsers();
        visitedUsers.setVisitStartDate(startDate1);
        visitedUsers.setVisitEndDate(endDate1);
        visitedUsers.setUserId(user.getId());
        List<Integer> list = visitUserService.findUsersId(visitedUsers);
        List<Users> list1 = new ArrayList<>();
        for (Integer usersId : list) {
            Users users = new Users();
            users.setUserId(user.getId());
            users.setId(usersId+"");
            Users users1 = visitUserService.findVisiter(users);
            list1.add(users1);
        }
        return RestResponse.ok(list1);
    }

    /**
     * 返回拜访人行动路线
     * @return
     */
    @RequestMapping(value = "visitUser", method = RequestMethod.POST)
    public RestResponse visitUser(String startDate,String endDate) throws ParseException {
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate1 = slf.parse(startDate);
        Date endDate1 = slf.parse(endDate);
        Integer userId = wxContext.getCurrentUser().getId();
        VisitedUsers visitedUsers = new VisitedUsers();
        visitedUsers.setVisitStartDate(startDate1);
        visitedUsers.setVisitEndDate(endDate1);
        visitedUsers.setUserId(userId);
        List<Integer> list = visitUserService.findUsersId(visitedUsers);
        List<Users> list1 = new ArrayList<>();
        for (Integer usersId : list) {
            Users users = new Users();
            users.setUserId(userId);
            users.setId(usersId+"");
            Users users1 = visitUserService.findVisiter(users);
            list1.add(users1);
        }
        return RestResponse.ok(list1);
    }


}
