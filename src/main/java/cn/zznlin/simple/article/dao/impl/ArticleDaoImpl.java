package cn.zznlin.simple.article.dao.impl;

import cn.zznlin.simple.article.dao.ArticleDao;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:32
 * @Description
 */
@Repository
public class ArticleDaoImpl extends HibernateDaoSupport<ArticleInfo> implements ArticleDao {
}
