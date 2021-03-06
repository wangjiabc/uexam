package com.alvis.exam.repository;

import com.alvis.exam.domain.dto.Integral.IntegralBasic;
import com.alvis.exam.domain.dto.UserDTO;
import com.alvis.exam.domain.dto.article.ExamDTO;
import com.alvis.exam.domain.other.KeyValue;
import com.alvis.exam.domain.User;
import com.alvis.exam.viewmodel.admin.user.UserPageRequestVM;
import com.alvis.exam.viewmodel.wx.student.user.QueryTimeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author alvis
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * getAllUser
     *
     * @return List<User>
     */
    List<User> getAllUser();

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

    User getUserByName(String username);

    /**
     * getUserByUserNamePwd
     *
     * @param username username
     * @param pwd      pwd
     * @return User
     */
    User getUserByUserNamePwd(@Param("username") String username, @Param("pwd") String pwd);

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
     * @param map userPageList
     * @return List<User>
     */
    List<User> userPageList(Map<String, Object> map);


    /**
     * userPageCount
     *
     * @param map map
     * @return Integer
     */
    Integer userPageCount(Map<String, Object> map);


    /**
     * @param requestVM requestVM
     * @return List<User>
     */
    List<User> userPage(UserPageRequestVM requestVM);


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
     * @param map map
     */
    void updateUsersAge(Map<String, Object> map);

    /**
     * deleteUsersByIds
     *
     * @param ids ids
     */
    void deleteUsersByIds(List<Integer> ids);

    ///**
    // * insertUserSql
    // *
    // * @param user user
    // */
    //void insertUserSql(User user);

    Integer selectAllCount();

    List<KeyValue> selectByUserName(String userName);

    List<User> selectByIds(List<Integer> ids);


    User selectByWxOpenId(@Param("wxOpenId") String wxOpenId);

    int calculateUserArticleScore(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String, Object>> calculateUsersArticleScore(Date startTime, Date endTime);


    /**
     * 根据时间查询用户积分排名
     * @param queryTimeVO
     */
    List<UserDTO> selectUserRanking(QueryTimeVO queryTimeVO);

    List<ExamDTO> selectExamRanking(QueryTimeVO queryTimeVO);

    int updateByPrimaryUser(User user);

    int updateByPrimaryImage(User user);


    IntegralBasic findYiRead(Integer id);

    IntegralBasic findZaiRead(Integer id);

    IntegralBasic findWeiRead(Integer id);


    IntegralBasic findYiExam(Integer id);

    List<User> findUser();

//    IntegralBasic findWeiExam(Integer id);
}
