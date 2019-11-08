package com.alvis.exam.viewmodel.student.article;

import com.alvis.exam.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto extends User {

    private static final long serialVersionUID = 0L;

    private Integer count;

    private Date startTime;

}
