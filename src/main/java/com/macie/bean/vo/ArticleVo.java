package com.macie.bean.vo;

import java.util.Date;

/**
 * @author Macie
 * @date 2020/9/25 -16:56
 */
public class ArticleVo {
    private Integer articleId;
    private String articleTitle;
    private String articleSlug;
    private String articleAuthor;
    private Date articleCreateTime;
    private Integer articleViewCount;
    private Integer articleCommentsCount;
    private String articleSummary;
    private String articleContentHtml;
    private String categoryName;
    private String articleContentMd;
    private Integer articleDelete;

    @Override
    public String toString() {
        return "ArticleVo{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleSlug='" + articleSlug + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleCreateTime=" + articleCreateTime +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentsCount=" + articleCommentsCount +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleContentHtml='" + articleContentHtml + '\'' +
                ", categoryId=" + categoryName +
                ", articleContentMd='" + articleContentMd + '\'' +
                ", articleDelete=" + articleDelete +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleSlug() {
        return articleSlug;
    }

    public void setArticleSlug(String articleSlug) {
        this.articleSlug = articleSlug;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentsCount() {
        return articleCommentsCount;
    }

    public void setArticleCommentsCount(Integer articleCommentsCount) {
        this.articleCommentsCount = articleCommentsCount;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public Integer getArticleDelete() {
        return articleDelete;
    }

    public void setArticleDelete(Integer articleDelete) {
        this.articleDelete = articleDelete;
    }

}
