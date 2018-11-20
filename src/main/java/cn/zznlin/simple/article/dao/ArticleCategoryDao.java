package cn.zznlin.simple.article.dao;

import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.common.orm.dao.BaseDao;

import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/11/20 23:30
 * @Description
 */
public interface ArticleCategoryDao extends BaseDao<ArticleCategoryInfo> {
    List<ArticleCategoryInfo> getNowArticleCategorys(Long articleId);
}
