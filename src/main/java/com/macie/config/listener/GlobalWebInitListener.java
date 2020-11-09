package com.macie.config.listener;

import com.macie.config.ProjectDeployConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Macie
 * @date 2020/11/4 -14:42
 */
public class GlobalWebInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        // 获取当前web部署根目录
        String deployRootDir = servletContext.getRealPath("/");
        System.out.println("deployRootDir:" + deployRootDir);
        //将部署根目录赋值给全局变量
        ProjectDeployConfig.setGlobalVariable(deployRootDir);
//        ProjectDeployConfig.setGlobalVariable(deployRootDir);
        //配置log4j
//        new Log4j2Config().startLog4j2Config();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
