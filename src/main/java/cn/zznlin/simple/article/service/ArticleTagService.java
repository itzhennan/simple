package cn.zznlin.simple.article.service;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;

/**
 * @Author zhennan
 * @Date 2018/10/28 22:51
 * @Description
 */
public interface ArticleTagService {

    void saveOrUpdateArticleTag(ArticleBean bean, ArticleInfo article);
}
