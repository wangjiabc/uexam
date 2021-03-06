package com.alvis.exam.service;

import com.alvis.exam.domain.dto.Integral.IntegralBasic;
import com.alvis.exam.domain.dto.article.UserDTO;
import com.alvis.exam.domain.dto.article.ExamDTO;
import com.alvis.exam.domain.other.KeyValue;
import com.alvis.exam.domain.User;
import com.alvis.exam.viewmodel.admin.user.UserPageRequestVM;
import com.alvis.exam.viewmodel.student.user.MessageRequestVM;
import com.alvis.exam.viewmodel.wx.student.user.QueryTimeVO;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author alvis
 */
public interface UserService extends BaseService<User> {

    /**
     * getUsers
     *
     * @return List<User>
     */
    List<User> getUsers();

    /**
     * getUserById
     *
     * @param id id
     * @return User
     */
    User getUserById(Integer id);

    /**
     * getUserByUserName
     *
     * @param username username
     * @return User
     */
    User getUserByUserName(String username);

    /**
     * getUserByUserName
     *
     * @param username username
     * @param pwd      pwd
     * @return User
     */
    User getUserByUserNamePwd(String username, String pwd);

    /**
     * getUserByUuid
     *
     * @param uuid uuid
     * @return User
     */
    User getUserByUuid(String uuid);

    /**
     * userPageList
     *
     * @param name      name
     * @param pageIndex pageIndex
     * @param pageSize  pageSize
     * @return List<User>
     */
    List<User> userPageList(String name, Integer pageIndex, Integer pageSize);


    /**
     * userPageCount
     *
     * @param name name
     * @return Integer
     */
    Integer userPageCount(String name);


    /**
     * @param requestVM requestVM
     * @return PageInfo<User>
     */
    PageInfo<User> userPage(UserPageRequestVM requestVM);


    /**
     * insertUser
     *
     * @param user user
     */
    void insertUser(User user);

    /**
     * insertUsers
     *
     * @param users users
     */
    void insertUsers(List<User> users);

    /**
     * updateUser
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * updateUsersAge
     *
     * @param age
     * @param ids
     */
    void updateUsersAge(Integer age, List<Integer> ids);

    /**
     * deleteUserByIds
     *
     * @param ids
     */
    void deleteUserByIds(List<Integer> ids);


    Integer selectAllCount();


    List<KeyValue> selectByUserName(String userName);


    List<User> selectByIds(List<Integer> ids);

    User selectByWxOpenId(String wxOpenId);

    Integer calculateUserScore(Integer userId, Date startTime, Date endTime);
    /**
     * @Author lz
     * @Description: 个人阅读积分
     * @param: [userId]
     * @Date: 2019/10/18 10:18
     *
     * @return*/
    Integer calculateUserArticleScore(Integer userId, Date startTime, Date endTime);

    List<Map<String,Object>> calculateUsersScore(Date startTime, Date endTime);

    List<Map<String, Object>> calculateUsersArticleScore(Date startTime, Date endTime);

    /**
     * 根据时间查询用户积分排名
     */

    PageInfo<UserDTO> selectUserRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM);

    PageInfo<ExamDTO> selectExamRanking(QueryTimeVO queryTimeVO, MessageRequestVM requestVM);

    User findUser(Integer id);

    void updateUserImage(User user);

    IntegralBasic userReadBasic(Integer id);

    IntegralBasic userExamBasic(Integer id);

    void updateByUser(User user);
}
