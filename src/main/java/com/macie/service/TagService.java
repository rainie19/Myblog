package com.macie.service;

import com.macie.bean.vo.ArticleVo;
import com.macie.bean.vo.TagVo;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/11/29 -17:58
 */
public interface TagService {

    /**
     * 将文章下的标签与文章id对应起来
     * @param articleVos
     * @return
     */
    public TreeMap<Integer, ArrayList<TagVo>> getArticleTagMap(ArrayList<ArticleVo> articleVos);
}
