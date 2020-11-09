package com.macie.servlet;

import com.macie.service.CategoryService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除分类
 * @author Macie
 * @date 2020/10/25 -17:32
 */
@WebServlet("/deleteCategory")
public class CategoryDeleteSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Integer categoryId = null;
        CategoryService categoryService = new CategoryService();
        if(req.getParameter("categoryId") != null) {
            categoryId = Integer.parseInt(req.getParameter("categoryId"));
        }
        //把要删除分类下的文章都移到默认分类中
        categoryService.updateCategory2Default(categoryId);
        categoryService.deleteCategoryById(categoryId);

        out.println(new JSONObject().accumulate("code", 20000));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
