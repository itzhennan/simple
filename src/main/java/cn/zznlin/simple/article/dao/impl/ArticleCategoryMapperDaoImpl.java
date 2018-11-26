package cn.zznlin.simple.article.dao.impl;

import cn.zznlin.simple.article.dao.ArticleCategoryMapperDao;
import cn.zznlin.simple.article.entity.ArticleCategoryMapperInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author zhennan zhang
 * @date 2018/11/24 15:24
 * @description
 */
@Repository
public class ArticleCategoryMapperDaoImpl extends HibernateDaoSupport<ArticleCategoryMapperInfo> implements ArticleCategoryMapperDao {
}
