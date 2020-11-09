package com.macie.servlet;

import com.macie.bean.vo.ArticleVo;
import com.macie.helper.JsonReponseHelper;
import com.macie.service.serviceImpl.ArticleUpdateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 修改或新增文章
 * @author Macie
 * @date 2020/10/20 -22:39
 */
@WebServlet("/publishArticle")

public class ArticleUpdateSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String articleDetail = req.getParameter("article");
        String publishType = req.getParameter("publishType");
        String dynamicTags = req.getParameter("dynamicTags");
        System.out.println("dynamicTags: "+dynamicTags);
        ArticleUpdateServiceImpl articleUpdateService = new ArticleUpdateServiceImpl();
        JsonReponseHelper jsonObject = new JsonReponseHelper();
        ArticleVo articleVo = jsonObject.convertJson2Bean(articleDetail, ArticleVo.class);
        ArrayList<String> tagList = jsonObject.convertString2Array(dynamicTags);
        ArticleVo responseArticleVo = articleUpdateService.publishArticle(articleVo, tagList, publishType);

        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseData("article", responseArticleVo);
        jsonReponse.setResponseOK();
        out.println(jsonReponse);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
