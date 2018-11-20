package cn.zznlin.simple.article.dao;

import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.common.orm.dao.BaseDao;

import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/11/20 23:32
 * @Description
 */
public interface ArticleTagDao extends BaseDao<ArticleTagInfo> {
    List<ArticleTagInfo> getNowArticleTags(Long articleId);
}
