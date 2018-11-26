package cn.zznlin.simple.article.dao.impl;

import cn.zznlin.simple.article.dao.ArticleTagMapperDao;
import cn.zznlin.simple.article.entity.ArticleTagMapperInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author zhennan zhang
 * @date 2018/11/24 15:28
 * @description
 */
@Repository
public class ArticleTagMapperDaoImpl extends HibernateDaoSupport<ArticleTagMapperInfo> implements ArticleTagMapperDao {
}
