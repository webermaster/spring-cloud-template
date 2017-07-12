package org.webermaster.spring.cloud.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kevin.weber on 7/5/2017.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@RestController
@RefreshScope
public class Application {

    @Value("${greeting}")
    private String property;

    @Value("${service.instance.name}")
    private String instanceName;

    @Autowired
    private CrudService service;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping
    public String get() {
        return property + "-Instance-" + instanceName + "(" + service.getCrud() + ")";
    }

}
