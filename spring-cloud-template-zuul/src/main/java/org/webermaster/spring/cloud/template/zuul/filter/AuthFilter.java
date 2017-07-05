package org.webermaster.spring.cloud.template.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.util.Arrays;
import java.util.Base64;

/**
 * Created by kevin.weber on 7/5/2017.
 */
public class AuthFilter extends ZuulFilter{
    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String[] split = ctx.getRequest().getServletPath().split("/");
        String serviceName = Arrays.stream(split, 1, split.length).findFirst()
                                    .orElse("");
        String auth = serviceName+":"+serviceName;
        String encoded = new String(Base64.getEncoder().encode(auth.getBytes()));
        ctx.addZuulRequestHeader("Authorization", "Basic " + encoded);
        return null;
    }
}
