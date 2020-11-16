package com.macie.servlet;

import com.macie.helper.JsonResponseHelper;
import com.macie.service.serviceImpl.TagServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除标签
 * @author Macie
 * @date 2020/10/25 -17:34
 */
@WebServlet("/deleteTag")
public class TagDeleteSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TagServiceImpl tagService = new TagServiceImpl();
        String tagName = req.getParameter("tagName");
        tagService.deleteTag(tagName);
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        jsonResponse.setResponseOK();
        out.println(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
