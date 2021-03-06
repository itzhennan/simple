package cn.zznlin.simple.article.dao.impl;

import cn.zznlin.simple.article.dao.ArticleTagDao;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:36
 * @Description
 */
@Repository
public class ArticleTagDaoImpl extends HibernateDaoSupport<ArticleTagInfo> implements ArticleTagDao {


}
