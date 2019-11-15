package com.alvis.exam.viewmodel.admin.user;

import com.alvis.exam.viewmodel.student.user.MessageRequestVM;

/**
 * 思维导入
 */
public class XmindVM extends MessageRequestVM {

    private static final long serialVersionUID = 0L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 路径
     */
    private String route;
    /**
     * 用户ID
     */
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
