package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.dao.ArticleTagDao;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleTagService;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.ValidateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/11/12 15:36
 * @description
 */
@Service
public class ArticleTagServiceImpl extends HibernateServiceSupport<ArticleTagInfo> implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    /**
     * 保存或更新个人分类
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleTag(ArticleBean bean, ArticleInfo article) {
        // 拿到未被删除的
        List<ArticleTagInfo> articleTags = articleTagDao.getNowArticleTags(article.getArticleId());

        if(articleTags == null){
            articleTags = Lists.newArrayList();
        }
        HashMap<String,ArticleTagInfo> tagMaps = Maps.newHashMap();
        for (ArticleTagInfo tag :articleTags ) {
            tag.setIsDel(1);
            tagMaps.put(tag.getTagName(), tag);
        }

        String[] tags = bean.getTag2().split(",");
        for (String tag: tags) {
            ArticleTagInfo tempTag = tagMaps.get(tag);
            if(ValidateUtils.isNotEmpty(tempTag)){
                tempTag.setIsDel(0);
            }else{
                tempTag = new ArticleTagInfo();
                tempTag.setTagName(tag);
                tempTag.setArticle(article);
                articleTagDao.save(tempTag);
            }
        }
        for (ArticleTagInfo tag :articleTags ) {
            if(tag.getIsDel() == 1){
                articleTagDao.update(tag);
            }
        }
    }
}
