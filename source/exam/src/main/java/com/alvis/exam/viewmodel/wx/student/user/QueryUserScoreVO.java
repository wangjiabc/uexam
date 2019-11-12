package com.alvis.exam.viewmodel.wx.student.user;

import com.alvis.exam.viewmodel.QueryTimeVO;
import lombok.Data;

/**
 * @author lz
 * @description
 * @date 2019/10/18
 */
@Data
public class QueryUserScoreVO extends QueryTimeVO {
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    Integer userId;
}
