package cn.zznlin.simple.article.dao.impl;

import cn.zznlin.simple.article.dao.ArticleCategoryDao;
import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:38
 * @Description
 */
@Repository
public class ArticleCategoryDaoImpl extends HibernateDaoSupport<ArticleCategoryInfo> implements ArticleCategoryDao {
    @Override
    public List<ArticleCategoryInfo> getNowArticleCategorys(Long articleId) {
        Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
        criteria.add(Restrictions.eq("article.articleId", articleId));
        criteria.add(Restrictions.eq("isDel", 0));
        return criteria.list();
    }
}
