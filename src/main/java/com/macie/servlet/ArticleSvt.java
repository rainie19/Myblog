package com.macie.servlet;

import com.macie.bean.vo.ArticleVo;
import com.macie.bean.vo.TagVo;
import com.macie.helper.JsonResponseHelper;
import com.macie.helper.ResponseCode;
import com.macie.service.serviceImpl.ArticleServiceImpl;
import com.macie.service.serviceImpl.TagServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 获取所有文章信息
 * @author Macie
 * @date 2020/9/26 -0:32
 */
@WebServlet(urlPatterns = "/getArticles")
public class ArticleSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer queryPage = null, pageSize = null;
        System.out.println(queryPage);
        String queryType = request.getParameter("queryType");
        String queryName = request.getParameter(queryType);
        if (request.getParameter("queryPage") != null) {
            queryPage = Integer.valueOf(request.getParameter("queryPage"));
        }
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        ArticleServiceImpl articleService = new ArticleServiceImpl();
        TagServiceImpl tagService = new TagServiceImpl();

        Long totalCount = articleService.countArticles(queryType, queryName);
        ArrayList<ArticleVo> articleVos = articleService.listArticles(queryType, queryPage, pageSize, queryName);
        for (ArticleVo article : articleVos) {
            System.out.println(article.getArticleCreateTime());
        }
        TreeMap<Integer, ArrayList<TagVo>> articleTagMap = null;
        if (articleVos != null) {
            articleTagMap = tagService.getArticleTagMap(articleVos);
        }
        JsonResponseHelper jsonResponse = new JsonResponseHelper();
        jsonResponse.setResponseData("articles", articleVos);
        System.out.println(jsonResponse);
        jsonResponse.setResponseData("articleTotalCount", totalCount);
        jsonResponse.setResponseData("articleIdTagsMap", articleTagMap);
        jsonResponse.setResponseCode(ResponseCode.CODE_SUCCESS);

        out.println(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
