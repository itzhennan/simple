package cn.zznlin.simple.article.dao;

import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:36
 * @Description
 */
@Repository(value = "ArticleTagDao")
public class ArticleTagDao extends HibernateDaoSupport<ArticleTagInfo> implements BaseDao<ArticleTagInfo> {
}
