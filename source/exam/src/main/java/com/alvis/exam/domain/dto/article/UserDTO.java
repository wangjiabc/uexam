package com.alvis.exam.domain.dto.article;

import com.alvis.exam.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends User {
    private Integer count;

    private Date startTime;

    private Integer rank;

    private static final long serialVersionUID = 0L;

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
