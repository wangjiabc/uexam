package com.alvis.exam.domain.dto.article;

import com.alvis.exam.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class ExamDTO extends User {

    private static final long serialVersionUID = 0L;

    private Integer userScore;

    private Date startTime;

    private Integer rank;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
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
