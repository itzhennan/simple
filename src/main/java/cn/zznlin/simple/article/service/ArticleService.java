package cn.zznlin.simple.article.service;

import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.base.entity.User;

/**
 * @Author zhennan
 * @Date 2018/10/28 22:51
 * @Description
 */
public interface ArticleService {
    void saveOrUpdateArticle(User user,ArticleBean bean, int isPub);
}