package com.alvis.exam.domain.dto;

import lombok.Data;

@Data
public class KvobjDTO {
    private Object value;
    private Object label;
    private Object children;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}
