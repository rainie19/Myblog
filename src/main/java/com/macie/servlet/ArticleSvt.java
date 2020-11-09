package com.macie.servlet;

import com.macie.bean.vo.ArticleVo;
import com.macie.bean.vo.TagVo;
import com.macie.helper.JsonReponseHelper;
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
        String queryType = request.getParameter("queryType");
        Integer queryPage = Integer.valueOf(request.getParameter("queryPage"));
        String queryName = request.getParameter(queryType);
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));

        ArticleServiceImpl articleService = new ArticleServiceImpl();
        TagServiceImpl tagService = new TagServiceImpl();

        Long totalCount = articleService.countArticles(queryType, queryName);
        ArrayList<ArticleVo> articleVos = articleService.listArticles(queryType, queryPage, pageSize, queryName);
        TreeMap<Integer, ArrayList<TagVo>> articleTagMap = tagService.getArticleTagMap(articleVos);
        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseData("articles", articleVos);
        jsonReponse.setResponseData("articleTotalCount", totalCount);
        jsonReponse.setResponseData("articleIdTagsMap", articleTagMap);
        jsonReponse.setResponseCode(ResponseCode.CODE_SUCCESS);

        out.println(jsonReponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
