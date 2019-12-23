package com.alvis.exam.domain;

import lombok.Data;

/**
 * 章节管理
 */
@Data
public class Chapter {

    private static final long serialVersionUID = 0L;

    /**
     * ID
     */
   private Integer id;
    /**
     * 名称
     */
   private String name;
    /**
     * 分类ID
     */
   private Integer typeId;
    /**
     * 排序
     */
   private Integer sequence;
    /**
     * 考试合格分
     */
    private Integer criterion;
    /**
     * 状态
     * @return
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getCriterion() {
        return criterion;
    }

    public void setCriterion(Integer criterion) {
        this.criterion = criterion;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
