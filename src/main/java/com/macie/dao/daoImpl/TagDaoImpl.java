package com.macie.dao.daoImpl;

import com.macie.bean.vo.TagVo;
import com.macie.dao.TagDao;
import com.macie.dao.constant.DatabaseConsts;
import com.macie.util.dbutil.DbChangeUtils;
import com.macie.util.dbutil.DbRetrieveUtils;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/30 -16:49
 */
public class TagDaoImpl implements TagDao {
    @Override
    public ArrayList<TagVo> retrieveAllTags() {
        String sql = "SELECT *, COUNT(*) AS tag_count " + " FROM " + DatabaseConsts.TABLE_TAGS + " GROUP BY " + DatabaseConsts.TAG_NAME;
        return DbRetrieveUtils.retrieveBeanListByParams(sql, null, TagVo.class);
    }

    @Override
    public Long countAllTags() {
        String sql = "SELECT COUNT(DISTINCT " + DatabaseConsts.TAG_NAME + ") FROM " + DatabaseConsts.TABLE_TAGS;
        return DbRetrieveUtils.retrieveBasicTypeByParams(sql, null, Long.class);
    }

    @Override
    public ArrayList<TagVo> retrieveTagsByArticleId(Integer articleId) {
        String sql = "SELECT " + DatabaseConsts.TAG_CONNECT_ID + ", " +
                DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.TAG_NAME + " FROM " +
                DatabaseConsts.TABLE_TAGS + " WHERE " + DatabaseConsts.ARTICLE_ID + " = ?";
        //String sql1 = "SELECT tags.tag_name, tags.tag_id FROM tags, article__tags WHERE article__tags.tag_id = tags.tag_id AND article__tags.article_id = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbRetrieveUtils.retrieveBeanListByParams(sql, list, TagVo.class);
    }

//    @Override
//    public int updateTagsByArticleTitle(String articleTitle, String tagName) {
//        String sql = "INSERT INTO " + DatabaseConsts.TABLE_ARTICLE__TAGS + "(" +
//                DatabaseConsts.ARTICLE_ID + ", " +
//                DatabaseConsts.TAG_ID + ") SELECT " +
//                DatabaseConsts.ARTICLE_ID + ", " + DatabaseConsts.TAG_ID+ " FROM " +
//                DatabaseConsts.TABLE_ARTICLE_DETAILS + ", " + DatabaseConsts.TABLE_TAGS + " WHERE " +
//                DatabaseConsts.ARTICLE_TITLE + " = ? AND " + DatabaseConsts.TAG_NAME + " = ?";
//        ArrayList<Object> list = new ArrayList<>();
//        list.add(articleTitle);
//        list.add(tagName);
//        return DbChangeUtils.changeDatabase(sql, list);
//    }
//
//    public int deleteTagsByArticleTitle(String articleName) {
//        int ret = -1;
//        String sql = "DELETE FROM " + DatabaseConsts.TABLE_ARTICLE__TAGS +
//                " WHERE " + DatabaseConsts.ARTICLE_ID +
//                " = SELECT " + DatabaseConsts.ARTICLE_ID +
//                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
//                " WHERE " + DatabaseConsts.ARTICLE_TITLE + " = ?";
//        return 0;
//    }

    @Override
    public int deleteTagsByArticleId(Integer articleId) {
        String sql = "DELETE FROM " + DatabaseConsts.TABLE_TAGS +
                " WHERE " + DatabaseConsts.ARTICLE_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int insertTagByArticleId(Integer articleId, String tagName) {
        String sql = "INSERT INTO " + DatabaseConsts.TABLE_TAGS + "(" +
                DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.TAG_NAME + ") VALUES (?, ?)";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        list.add(tagName);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int insertNewTag(String tagName) {
        String sql = "INSERT INTO " + DatabaseConsts.TABLE_TAGS + " (" +
                DatabaseConsts.TAG_NAME + ") VALUES ( ? )";
        ArrayList<Object> list = new ArrayList<>();
        list.add(tagName);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int deleteTag(String tagName) {
        String sql = "DELETE FROM " + DatabaseConsts.TABLE_ARTICLE__TAGS +
                " WHERE " + DatabaseConsts.TAG_NAME + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(tagName);
        return DbChangeUtils.changeDatabase(sql, list);
    }
}
