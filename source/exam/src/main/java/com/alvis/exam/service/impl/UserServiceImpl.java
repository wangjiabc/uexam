package com.alvis.exam.service.impl;

import com.alvis.exam.domain.User;
import com.alvis.exam.domain.dto.Integral.IntegralBasic;
import com.alvis.exam.domain.dto.article.UserDTO;
import com.alvis.exam.domain.dto.article.ExamDTO;
import com.alvis.exam.domain.other.KeyValue;
import com.alvis.exam.event.OnRegistrationCompleteEvent;
import com.alvis.exam.exception.BusinessException;
import com.alvis.exam.repository.ExamPaperAnswerMapper;
import com.alvis.exam.repository.ExamPaperMapper;
import com.alvis.exam.repository.UserMapper;
import com.alvis.exam.service.UserService;
import com.alvis.exam.viewmodel.admin.user.UserPageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.alvis.exam.viewmodel.wx.student.user.QueryTimeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alvis
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final static String CACHE_NAME = "User";
    private final UserMapper userMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Resource
    private ExamPaperAnswerMapper examPaperAnswerMapper;
    @Resource
    private UserService userService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, ApplicationEventPublisher eventPublisher) {
        super(userMapper);
        this.userMapper = userMapper;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public List<User> getUsers() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
//    @Cacheable(value = CACHE_NAME, key = "#username")
    public User getUserByUserName(String username) {
//        return userMapper.getUserByUserName(username);
        return userMapper.getUserByName(username);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#record.userName")
    public int insertByFilter(User record) {
        return super.insertByFilter(record);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#record.userName")
    public int updateByIdFilter(User record) {
        return super.updateByIdFilter(record);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#record.userName")
    public int updateById(User record) {
        return super.updateById(record);
    }

    @Override
    public User getUserByUserNamePwd(String username, String pwd) {
        return userMapper.getUserByUserNamePwd(username, pwd);
    }

    @Override
    public User getUserByUuid(String uuid) {
        return userMapper.getUserByUuid(uuid);
    }

    @Override
    public List<User> userPageList(String name, Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("offset", ((int) pageIndex) * pageSize);
        map.put("limit", pageSize);
        return userMapper.userPageList(map);
    }

    @Override
    public Integer userPageCount(String name) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        return userMapper.userPageCount(map);
    }


    @Override
    public PageInfo<User> userPage(UserPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                userMapper.userPage(requestVM)
        );
    }


    @Override
    public void insertUser(User user) {
        userMapper.insertSelective(user);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void insertUsers(List<User> users) {
        userMapper.insertUsers(users);
        throw new BusinessException("test BusinessException roll back");
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryUser(user);
    }

    @Override
    public void updateUsersAge(Integer age, List<Integer> ids) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("idslist", ids);
        map.put("age", age);
        userMapper.updateUsersAge(map);
    }

    @Override
    public void deleteUserByIds(List<Integer> ids) {
        userMapper.deleteUsersByIds(ids);
    }

    @Override
    public Integer selectAllCount() {
        return userMapper.selectAllCount();
    }

    @Override
    public List<KeyValue> selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public List<User> selectByIds(List<Integer> ids) {
        return userMapper.selectByIds(ids);
    }

    @Override
    public User selectByWxOpenId(String wxOpenId) {
        return userMapper.selectByWxOpenId(wxOpenId);
    }

    /**
     * @Author lz
     * @Description: 计算单个用户积分
     * @param: [userId, startTime, endTime]
     * @Date: 2019/11/7 14:20
     **/
    @Override
    public Integer calculateUserScore(Integer userId, Date startTime, Date endTime) {
        //int  examPaperScore= examPaperAnswerMapper.calculateExamPaperScore(userId,starTime,endTime);
        return userService.calculateUserArticleScore(userId, startTime, endTime);
    }

    /**
     * @Author lz
     * @Description: 计算单个用户阅读积分
     * @param: [userId, startTime, endTime]
     * @Date: 2019/11/7 14:20
     **/
    @Override
    public Integer calculateUserArticleScore(Integer userId, Date startTime, Date endTime) {
        return userMapper.calculateUserArticleScore(userId, startTime, endTime);
    }

    /**
     * @Author lz
     * @Description: 计算所有用户积分
     * @param: [userId, startTime, endTime]
     * @Date: 2019/11/7 14:20
     **/
    @Override
    public List<Map<String, Object>> calculateUsersScore(Date startTime, Date endTime) {
        return userService.calculateUsersArticleScore(startTime, endTime);
    }

    /**
     * @Author lz
     * @Description: 计算所有用户阅读积分
     * @param: [userId, startTime, endTime]
     * @Date: 2019/11/7 14:20
     **/
    @Override
    public List<Map<String, Object>> calculateUsersArticleScore(Date startTime, Date endTime) {
        return userMapper.calculateUsersArticleScore(startTime, endTime);
    }

    /**
     *根据时间查询用户积分排名
     */

    @Override
    public PageInfo<UserDTO> selectUserRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM){
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "count desc").doSelectPageInfo(() ->
                userMapper.selectUserRanking(queryTimeVO)
        );
    }

    @Override
    public PageInfo<ExamDTO> selectExamRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "userScore desc").doSelectPageInfo(() ->
                userMapper.selectExamRanking(queryTimeVO)
        );
    }

    @Override
    public User findUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void updateUserImage(User user) {
        userMapper.updateByPrimaryImage(user);
    }

    @Override
    public IntegralBasic  userReadBasic(Integer id) {
        IntegralBasic a1 = userMapper.findYiRead(id);        //已读
        IntegralBasic a2 = userMapper.findZaiRead(id);       //在读
        IntegralBasic a3 = userMapper.findWeiRead(id);       //未读
        Integer integralCount = a1.getIntegralCount();          //積分
        Integer count2 = a2.getCount2();
        Integer count3 = a3.getCount3();
        if(integralCount == null){
            integralCount = 0;
            a1.setIntegralCount(integralCount);
        }
        if(count2 == null){
            count2 = 0;
        }
        if(count3 == null){
            count3 = 0;
        }
        a1.setCount2(count2);
        a1.setCount3(count3);
        return a1;
    }

    @Resource
    private ExamPaperMapper examPaperMapper;

    @Override
    public IntegralBasic userExamBasic(Integer id) {
        IntegralBasic a1 = userMapper.findYiExam(id);           //已考
        IntegralBasic a3 = examPaperMapper.findAll();           //总考试科目数
        Integer count3 = a3.getCount3();
        Integer integralCount = a1.getIntegralCount();          //積分
        if(count3 == null){
            count3 = 0;
        }
        if(integralCount == null){
            integralCount = 0;
            a1.setIntegralCount(integralCount);
        }
        else {
            a1.setIntegralCount(integralCount/10);
        }
        int a = count3 - a1.getCount1();        //未考
        a1.setCount3(a);
        return a1;
    }
}
