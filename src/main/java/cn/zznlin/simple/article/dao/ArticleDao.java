package cn.zznlin.simple.article.dao;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/12 23:32
 * @Description
 */
@Repository(value = "ArticleDao")
public class ArticleDao extends HibernateDaoSupport<ArticleInfo> implements BaseDao<ArticleInfo>{
}
