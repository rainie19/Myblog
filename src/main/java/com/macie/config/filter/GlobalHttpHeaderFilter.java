package com.macie.config.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 预处理CharsetEncoding和Content-Type
 * @author Macie
 * @date 2020/10/29 -20:42
 */
public class GlobalHttpHeaderFilter implements Filter {
    private static String encoding;
    private static String contentType;
    private static String baseUrl;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("CharsetEncoding");
        contentType = filterConfig.getInitParameter("ContentType");
        String baseUrlTemp = filterConfig.getInitParameter("baseUrl");
        baseUrl = baseUrlTemp.endsWith("/") ? baseUrlTemp : baseUrlTemp + "/";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        // 静态资源不进行过滤
        if(uri.indexOf(".") > 0 || baseUrl.equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        }
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        servletResponse.setContentType(contentType);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
