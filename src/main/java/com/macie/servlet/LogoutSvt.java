package com.macie.servlet;

import com.macie.helper.JsonResponseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登出
 * @author Macie
 * @date 2020/10/3 -11:59
 */
@WebServlet("/logout")
public class LogoutSvt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        jsonResponse.setResponseOK();
        out.println(jsonResponse);
    }
}

