package com.macie.service;

import com.macie.bean.vo.ArticleVo;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/10/29 -10:20
 */
public interface ArticleUpdateService {

    public ArticleVo publishArticle(ArticleVo articleVo, ArrayList<String> tagList, String publishType);

//    public int insertArticle(ArticleVo articleVo);
}
