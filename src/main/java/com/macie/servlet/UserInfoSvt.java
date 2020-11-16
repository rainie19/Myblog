package com.macie.servlet;

import com.macie.bean.vo.UserInfoVo;
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
 * @author Macie
 * @date 2020/10/2 -16:30
 */

@WebServlet("/getUserInfo")
public class UserInfoSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String userName = req.getParameter("userName");
        UserInfoService userInfoService = new UserInfoService();
        UserInfoVo userInfoVo = userInfoService.retrieveUserInfoByUserName(userName);
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        jsonResponse.setResponseOK("userInfo", userInfoVo);
        out.println(jsonResponse);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
