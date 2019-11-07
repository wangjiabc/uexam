package com.alvis.exam.configuration.property;

import lombok.Data;

@Data
public class UrlConfig {
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
