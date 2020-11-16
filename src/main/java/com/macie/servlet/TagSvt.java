package com.macie.servlet;

import com.macie.bean.vo.TagVo;
import com.macie.helper.JsonResponseHelper;
import com.macie.service.serviceImpl.TagServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 获取所有tags
 * @author Macie
 * @date 2020/9/30 -16:56
 */
@WebServlet("/getAllTags")
public class TagSvt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        TagServiceImpl tagService = new TagServiceImpl();
        ArrayList<TagVo> tagVos = tagService.listAllTags();
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        jsonResponse.setResponseOK("tags", tagVos);
        out.println(jsonResponse);
    }
}
