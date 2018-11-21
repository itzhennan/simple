package cn.zznlin.simple.article.service;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.pojo.ArticleCond;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.bean.Page;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/10/28 22:51
 * @Description
 */
public interface ArticleService {
    void saveOrUpdateArticle(User user,ArticleBean bean, int isPub) throws UnsupportedEncodingException;

    ArticleInfo get(String articleId);

    List<ArticleBean> findIndex(ArticleCond cond,Page page);
}
