package cn.zznlin.simple.article.dao;

import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:38
 * @Description
 */
@Repository(value = "ArticleCategoryDao")
public class ArticleCategoryDao extends HibernateDaoSupport<ArticleCategoryInfo> implements BaseDao<ArticleCategoryInfo> {
}
