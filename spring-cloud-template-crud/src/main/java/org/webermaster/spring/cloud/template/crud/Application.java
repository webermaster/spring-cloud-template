package org.webermaster.spring.cloud.template.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin.weber on 7/5/2017.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
public class Application {

    @Autowired
    private AppConfig config;

    @Value("${greeting.part2}")
    private String property;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping
    public String get() {
        return config.getPart1() + property;
    }
}
