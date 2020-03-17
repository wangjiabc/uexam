package com.alvis.exam.service.impl;

import com.alvis.exam.domain.User;
import com.alvis.exam.domain.Users;
import com.alvis.exam.domain.VisitedUsers;
import com.alvis.exam.domain.dto.VisitUsersDTO;
import com.alvis.exam.repository.UsersMapper;
import com.alvis.exam.repository.VisitedUsersMapper;
import com.alvis.exam.service.VisitUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitUserServiceImpl implements VisitUserService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private VisitedUsersMapper visitedUsersMapper;

    @Override
    public List<VisitUsersDTO> findVisits() {
        List<VisitUsersDTO> list = usersMapper.findVisits();
        return list;
    }

    @Override
    public void saveStartDate(VisitedUsers visitedUsers) {
        visitedUsersMapper.insert(visitedUsers);
    }

    @Override
    public void saveEndDate(VisitedUsers visitedUsers) {
        visitedUsersMapper.updateByVisitedUsers(visitedUsers);
    }

    @Override
    public VisitedUsers findTime(VisitedUsers visitedUsers) {
        VisitedUsers visitedUsers1 = visitedUsersMapper.findTime(visitedUsers);
        return visitedUsers1;
    }

    @Override
    public Users findCon(Integer usersId) {
        Users users = usersMapper.findConById(usersId);
        return users;
    }

    @Override
    public VisitedUsers findMaxTime(VisitedUsers visitedUsers) {
        return visitedUsersMapper.findMaxTime(visitedUsers);
    }

    @Override
    public List<Integer> findUsersId(VisitedUsers visitedUsers) {
        return visitedUsersMapper.findUsersId(visitedUsers);
    }

    @Override
    public Users findVisiter(Users users) {
        return usersMapper.findVisiter(users);
    }

    @Override
    public List<User> findUser() {
        return usersMapper.findUser();
    }
}
