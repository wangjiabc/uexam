package com.alvis.exam.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="url") //接收application.yml中的myProps下面的属性

@Data
public class Property {
    private String ip;

}
