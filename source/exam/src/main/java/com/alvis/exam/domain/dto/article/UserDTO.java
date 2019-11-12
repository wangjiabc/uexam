package com.alvis.exam.domain.dto.article;

import com.alvis.exam.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends User {

    private static final long serialVersionUID = 0L;

    private Integer count;

    private Date startTime;

    private Integer rank;

}
