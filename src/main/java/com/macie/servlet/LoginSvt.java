package com.macie.servlet;

import com.macie.helper.JsonResponseHelper;
import com.macie.service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登入
 * @author Macie
 * @date 2020/10/2 -17:02
 */
@WebServlet("/login")
public class LoginSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String userName = req.getParameter("userName");
        String passWord = req.getParameter("password");
        UserInfoService userInfoService = new UserInfoService();
        String decodePwd = userInfoService.retrievePwdByUserName(userName);
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        if(decodePwd != null && decodePwd.equals(passWord)) {
            jsonResponse.setResponseOK("token", passWord);
            req.getSession().setAttribute("userName", userName);
        }
        else {
            jsonResponse.setResponseFailed("账号或密码错误");
        }
        out.println(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
