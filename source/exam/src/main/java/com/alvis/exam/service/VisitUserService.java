package com.alvis.exam.service;


import com.alvis.exam.domain.User;
import com.alvis.exam.domain.Users;
import com.alvis.exam.domain.VisitedUsers;
import com.alvis.exam.domain.dto.VisitUsersDTO;

import java.util.Date;
import java.util.List;

public interface VisitUserService {
    List<VisitUsersDTO> findVisits();

    void saveStartDate(VisitedUsers visitedUsers);

    void saveEndDate(VisitedUsers visitedUsers);

    VisitedUsers findTime(VisitedUsers visitedUsers);

    Users findCon(Integer usersId);

    VisitedUsers findMaxTime(VisitedUsers visitedUsers);

    List<Integer> findUsersId(VisitedUsers visitedUsers);

    Users findVisiter(Users users);

    List<User> findUser();
}
