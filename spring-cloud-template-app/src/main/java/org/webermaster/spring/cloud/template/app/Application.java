package org.webermaster.spring.cloud.template.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@RestController
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RibbonConfig.class)
})
//@RibbonClient(name = "time-service", configuration = RibbonConfig.class)
@RefreshScope
public class Application {

    @Autowired
    private ServiceClient client;

    @Value("${greeting}")
    private String greeting;

    @Value("${hello}")
    private String hello;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping
    public String get() {
        return hello + " from " + greeting + "(" + client.getServiceName() + ")";
    }
}
