package org.webermaster.spring.cloud.template.app;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * Created by kevin.weber on 7/5/2017.
 */
@Service
@RefreshScope
public class ServiceClient {

    @Value("${service.username}")
    public String serviceUsername;

    @Value("${service.password}")
    public String servicePassword;

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "unknown")
    public String getServiceName() {
        String encoded = new String(Base64.getEncoder()
                .encode((serviceUsername + ":" + servicePassword).getBytes()));
        return restTemplate.exchange("http://service", HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders() {{
                    add("Authorization", "Basic " + encoded);
                }}), String.class).getBody();
    }

    public String unknown() {
        return "unknown";
    }

}
