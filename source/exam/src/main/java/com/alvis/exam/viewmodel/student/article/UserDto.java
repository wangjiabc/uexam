package com.alvis.exam.viewmodel.student.article;

import com.alvis.exam.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto extends User {

    private static final long serialVersionUID = 0L;

    private Integer count;

    private Date startTime;

    private Integer rank;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }


}
