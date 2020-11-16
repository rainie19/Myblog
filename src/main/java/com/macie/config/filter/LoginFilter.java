package com.macie.config.filter;

import com.macie.helper.JsonResponseHelper;
import com.macie.helper.ResponseCode;
import com.macie.service.UserInfoService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截未登录用户过滤器
 * @author Macie
 * @date 2020/9/28 -11:25
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object userName = session.getAttribute("userName");
        Cookie[] cookies = request.getCookies();
        String uri = request.getRequestURI();
        UserInfoService userInfoService = new UserInfoService();
        Boolean isLogin = false;

        System.out.println("filter uri:"+ uri);
        System.out.println("sessionId:" + session.getId());
        String passWd = userInfoService.retrievePwdByUserName(String.valueOf(userName));
        for(Cookie c : cookies) {
            if("blogToken".equals(c.getName()) && c.getValue().equals(passWd)) {
                isLogin = true;
                break;
            }
        }
        if(isLogin) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            JsonResponseHelper jsonResponse = new JsonResponseHelper();
            jsonResponse.setResponseCode(ResponseCode.CODE_ILLEGAL_TOKEN);
            response.getWriter().println(jsonResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
