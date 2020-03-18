package com.alvis.exam.domain.dto.Integral;

import lombok.Data;

import java.io.Serializable;

@Data
public class IntegralBasic implements Serializable {
    private Integer integralCount;  //总积分
    private Integer count1;     //已读/已考
    private Integer count2;     //在读
    private Integer count3;     //未读/未考
}
