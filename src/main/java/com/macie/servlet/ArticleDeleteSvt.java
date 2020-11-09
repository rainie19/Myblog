package com.macie.servlet;

import com.macie.helper.JsonReponseHelper;
import com.macie.service.serviceImpl.ArticleDeleteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除文章
 * @author Macie
 * @date 2020/10/25 -17:01
 */
@WebServlet("/deleteArticle")
public class ArticleDeleteSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Integer articleId = null;
        if(req.getParameter("articleId") != null) {
            articleId = Integer.parseInt(req.getParameter("articleId"));
        }
        ArticleDeleteServiceImpl articleService = new ArticleDeleteServiceImpl();
        articleService.deleteArticle(articleId);
        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseOK();
        out.println(jsonReponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
