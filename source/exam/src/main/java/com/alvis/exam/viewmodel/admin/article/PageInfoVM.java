package com.alvis.exam.viewmodel.admin.article;

import com.github.pagehelper.PageInfo;
import lombok.Data;

@Data
public class PageInfoVM<T> extends PageInfo {
    private String type;
}
