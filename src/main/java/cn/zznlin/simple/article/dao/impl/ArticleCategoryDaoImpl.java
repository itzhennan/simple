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

}
