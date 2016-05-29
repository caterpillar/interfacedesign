package org.interfacedesign.filter;

import org.apache.commons.lang3.StringUtils;
import org.interfacedesign.assembler.UserInfo;
import org.interfacedesign.base.exception.ResponseStatusCode;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by lishaohua on 16-5-28.
 */
public class AuthFilter implements Filter{
    private String[] exclude;

    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeStr = filterConfig.getInitParameter("exclude");
        this.exclude = excludeStr.split(",");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String requestURL = request.getRequestURL().toString();
        String[] urlArray = requestURL.split("/");
        for(String exc : exclude) {
            for(String reqUrl : urlArray) {
                 if(reqUrl.equals(exc)) {
                     filterChain.doFilter(servletRequest, servletResponse);
                     return;
                 }
            }
        }
        String token = request.getHeader("token");
        servletResponse.setContentType("application/json;charset=UTF-8");
        if(StringUtils.isEmpty(token)) {
            response.setStatus(ResponseStatusCode.UNAUTHORIZED);
            return;
        } else {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(token);
            if(userInfo == null) {
                response.setStatus(ResponseStatusCode.UNAUTHORIZED);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    public void destroy() {

    }
}
