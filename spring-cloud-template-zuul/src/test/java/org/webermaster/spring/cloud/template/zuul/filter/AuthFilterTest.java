package org.webermaster.spring.cloud.template.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin.weber on 7/5/2017.
 */
public class AuthFilterTest {

    private HttpServletRequest req = new MockHttpServletRequest();

    private AuthFilter underTest = new AuthFilter();

    @Before
    public void before() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.clear();
        ctx.setRequest(req);
    }

    @Test
    public void testRun() {
        assertEquals(0, underTest.filterOrder());
        assertEquals(true, underTest.shouldFilter());
        assertEquals("pre", underTest.filterType());
        assertEquals(null, underTest.run());
    }
}