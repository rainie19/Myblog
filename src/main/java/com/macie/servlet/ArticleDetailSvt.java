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

/**
 * 获取单篇文章具体内容
 * @author Macie
 * @date 2020/9/27 -1:53
 */
@WebServlet("/getArticleDetail")
public class ArticleDetailSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        Integer articleId = Integer.parseInt(req.getParameter("articleId"));
        ArticleServiceImpl articleService = new ArticleServiceImpl();
        TagServiceImpl tagService = new TagServiceImpl();

        ArticleVo articleVo = articleService.getArticle(articleId);
        // 映射article_id和tags
        ArrayList<TagVo> tagVoArrayList = tagService.retrieveTagsByArticleId(articleId);
        //返回上一篇文章和下一篇文章信息
        ArticleVo prevArticleVo = articleService.getPreviousArticle(articleId);
        ArticleVo nextArticleVo = articleService.getNextArticle(articleId);

        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseData("article", articleVo);
        jsonReponse.setResponseData("prevArticle", prevArticleVo);
        jsonReponse.setResponseData("nextArticle", nextArticleVo);
        jsonReponse.setResponseData("tags", tagVoArrayList);
        jsonReponse.setResponseCode(ResponseCode.CODE_SUCCESS);
        out.println(jsonReponse);
        // response返回后增加文章阅读次数
        articleService.updateArticleViewCount(articleId);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
