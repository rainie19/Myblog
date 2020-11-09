package com.macie.servlet;

import com.macie.bean.vo.CategoryVo;
import com.macie.helper.JsonReponseHelper;
import com.macie.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 获取所有分类
 * @author Macie
 * @date 2020/9/29 -17:14
 */
@WebServlet("/getAllCategories")
public class CategorySvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        CategoryService categoryService = new CategoryService();
        ArrayList<CategoryVo> categoryVos = categoryService.retrieveAllCategories();
        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        jsonReponse.setResponseOK("categories" , categoryVos);

        out.println(jsonReponse);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
