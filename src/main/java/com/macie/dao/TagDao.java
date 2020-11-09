package com.macie.dao;

import com.macie.bean.vo.TagVo;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/30 -16:37
 */
public interface TagDao {
    /**
     *
     * @return
     */
    public ArrayList<TagVo> retrieveAllTags();

    /**
     *
     * @param articleId
     * @return
     */
    public ArrayList<TagVo> retrieveTagsByArticleId(Integer articleId);

    /**
     *根据文章标题更新文章的tags
     * @param articleTitle
     * @param tagName
     * @return
     */
    //public int updateTagsByArticleTitle(String articleTitle, String tagName);

    /**
     * 删除文章下所有tag
     * @param articleId
     * @return
     */
    public int deleteTagsByArticleId(Integer articleId);

    /**
     *插入具体文章tag
     * @param articleId
     * @param tagName
     * @return
     */
    public int insertTagByArticleId(Integer articleId, String tagName);
    /**
     * 往Database中添加新标签
     * @param tagName
     * @return
     */
    public int insertNewTag(String tagName);
    /**
     *在所有地方删除此tag
     * @param tagName
     * @return
     */
    public int deleteTag(String tagName);
}
