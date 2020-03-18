package com.alvis.exam.domain.other;

import lombok.Data;

@Data
public class KeyValue {

    private String name;
    private Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}
