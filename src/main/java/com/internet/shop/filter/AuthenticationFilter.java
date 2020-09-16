package com.internet.shop.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(initParams =
            @WebInitParam(name = "urls", value = "/index,/login,/register"),
                urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "userId";
    private List<String> excludeUrls;

    @Override
    public void init(FilterConfig filterConfig) {
        excludeUrls = Arrays.asList(filterConfig.getInitParameter("urls").split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String servletPath = req.getServletPath();
        if (!excludeUrls.contains(servletPath)
                && req.getSession().getAttribute(USER_ID) == null) {
            resp.sendRedirect("/index");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
