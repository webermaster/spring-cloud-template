package org.webermaster.spring.cloud.template.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
public class CrudService {

    @Value("${crud.username}")
    private String crudUsername;

    @Value("${crud.password}")
    private String crudPassword;

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "unknown")
    public String getCrud() {
        InstanceInfo ii = client.getNextServerFromEureka("crud", false);
        String encoded = new String(Base64.getEncoder()
                                          .encode((crudUsername + ":" + crudPassword).getBytes()));
        return restTemplate
                .exchange(ii.getHomePageUrl(), HttpMethod.GET,
                        new HttpEntity<>(null, new HttpHeaders() {{
                            add("Authorization", "Basic " + encoded);
                        }}), String.class).getBody();
    }

    public String unknown() {
        return "unknown";
    }
}
