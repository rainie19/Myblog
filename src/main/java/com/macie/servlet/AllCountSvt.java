package com.macie.servlet;

import com.macie.helper.JsonReponseHelper;
import com.macie.service.AllCountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

/**
 * 获取概览文章,分类，标签数
 *
 * @author Macie
 * @date 2020/10/23 -18:58
 */
@WebServlet("/getAllCount")
public class AllCountSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        AllCountService articlesCountService = new AllCountService();
        TreeMap<String, Long> countMap = articlesCountService.getAllCount();
        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseOK("AllCount", countMap);

        out.println(jsonReponse);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
