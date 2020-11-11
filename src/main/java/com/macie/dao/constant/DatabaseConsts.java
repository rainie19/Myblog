package com.macie.dao.constant;

/**
 * @author Macie
 * @date 2020/10/20 -23:20
 */
public class DatabaseConsts {
    /* constants in table article_details */
    public static final String TABLE_ARTICLE_DETAILS = "article_details";

    public static final String ARTICLE_ID = "article_id";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String ARTICLE_SLUG = "article_slug";
    public static final String ARTICLE_AUTHOR = "article_author";
    public static final String ARTICLE_CREATE_TIME = "article_create_time";
    public static final String ARTICLE_TAG = "article_tag";
    public static final String ARTICLE_VIEW_COUNT = "article_view_count";
    public static final String ARTICLE_COMMENTS_COUNT = "article_comments_count";
    public static final String ARTICLE_SUMMARY = "article_summary";
    public static final String ARTICLE_CONTENT_HTML = "article_content_html";
    public static final String ARTICLE_CONTENT_MD = "article_content_md";
    public static final String ARTICLE_DELETE = "article_delete";


    /* constants in table blog_category */
    public static final String TABLE_BLOG_CATEGORY = "blog_category";

    public static final String DEFAULT_CATEGORY = "默认分类";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CREATE_DATE = "create_date";
    public static final String CATEGORY_DELETE = "category_delete";

    /* constants in table tags */
    public static final String TABLE_TAGS = "tags";
    public static final String TAG_CONNECT_ID = "tag_connect_id";
    public static final String TAG_NAME = "tag_name";

    /* constants in table article__tags */
    public static final String TABLE_ARTICLE__TAGS = "article__tags";

    public static final String CONNECT_ID = "connect_id";

    /* constants in table user_info */
    public static final String TABLE_USER_INFO = "user_info";

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_AGE = "user_age";
    public static final String USER_SEX = "user_sex";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_HEAD_URL = "user_head_url";
    public static final String USER_NICK_NAME = "user_nick_name";
    public static final String USER_AVATAR_PATH = "user_avatar_path";
}
