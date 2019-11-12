package com.alvis.exam.module;

import com.alvis.exam.ExamApplicationTests;
import com.alvis.exam.domain.User;
import com.alvis.exam.service.UserService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.viewmodel.admin.user.UserPageRequestVM;
import com.alvis.exam.viewmodel.student.article.UserDto;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;
import com.voucher.manage.tools.MyTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamApplicationTests.class)
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    public void userPageListTest() {
        User user = userService.getUserById(2);
        UserPageRequestVM requestVM = new UserPageRequestVM();
        requestVM.setUserName("李");
        requestVM.setPageIndex(0);
        requestVM.setPageSize(2);
        PageInfo<User> pageInfo = userService.userPage(requestVM);
        MyTestUtil.print(pageInfo);
        //Assert.assertNotEquals(0, pageInfo.getTotal());
    }

    @Test
    public void test() {
        MessageRequestVM requestVM = new MessageRequestVM();
        requestVM.setReceiveUserId(24);
        requestVM.setPageIndex(1);
        requestVM.setPageSize(10);
        PageInfo<UserDto> userDtoPageInfo = userService.selectUserRanking(DateTimeUtil.getMonthStartDay(), new Date(), requestVM);
        //for (UserDto userDto : userDtoPageInfo.getList()) {
        //    userDto.setRank(1);
        //}
        System.out.println(userDtoPageInfo.getList());

    }


}
