package com.alvis.exam.viewmodel;

import com.alvis.exam.utility.DateTimeUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     Date  startTime = DateTimeUtil.getMonthStartDay();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     Date endTime = DateTimeUtil.getMonthEndDay();
}
