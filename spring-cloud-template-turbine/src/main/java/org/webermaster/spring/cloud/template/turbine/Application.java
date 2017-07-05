package org.webermaster.spring.cloud.template.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by kevin.weber on 7/5/2017.
 */
@SpringBootApplication
@EnableTurbine
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
