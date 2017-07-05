package org.webermaster.spring.cloud.template.crud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by kevin.weber on 7/3/2017.
 */
@Component
@ConfigurationProperties(prefix = "greeting")
public class AppConfig {

    private String part1;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }
}

