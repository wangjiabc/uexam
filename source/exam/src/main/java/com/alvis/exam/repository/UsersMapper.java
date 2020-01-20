package com.alvis.exam.repository;

import com.alvis.exam.domain.User;
import com.alvis.exam.domain.Users;
import com.alvis.exam.domain.dto.VisitUsersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    int insert(Users record);

    int insertSelective(Users record);

    List<VisitUsersDTO> findVisits();

    Users findConById(Integer usersId);

    Users findVisiter(Users users);

    List<User> findUser();
}