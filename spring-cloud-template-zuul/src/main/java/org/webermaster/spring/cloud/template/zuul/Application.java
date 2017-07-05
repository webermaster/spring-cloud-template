package org.webermaster.spring.cloud.template.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.webermaster.spring.cloud.template.zuul.filter.AuthFilter;

/**
 * Created by kevin.weber on 7/5/2017.
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
