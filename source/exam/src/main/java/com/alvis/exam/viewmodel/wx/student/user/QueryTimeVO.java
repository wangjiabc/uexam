package com.alvis.exam.viewmodel.wx.student.user;

import com.alvis.exam.utility.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author lz
 * @description
 * @date 2019/10/18
 */
@Data
public class QueryTimeVO {
    //@JsonFormat用于单独配置,会覆盖全局配置
    //@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    Date startTime = DateTimeUtil.getMonthStartDay();
    Date endTime;
}
