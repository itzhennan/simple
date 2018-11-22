package cn.zznlin.simple.article.service;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.common.orm.service.BaseService;

/**
 * @Author zhennan
 * @Date 2018/10/28 22:51
 * @Description
 */
public interface ArticleTagService extends BaseService<ArticleTagInfo> {

    void saveOrUpdateArticleTag(ArticleBean bean, ArticleInfo article);
}
