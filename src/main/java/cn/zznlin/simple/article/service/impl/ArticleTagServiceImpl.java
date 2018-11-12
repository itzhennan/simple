package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleTagService;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.utils.ValidateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018/11/12 15:36
 * @description
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource(name = "ArticleTagDao")
    private BaseDao<ArticleTagInfo> articleTagDao;

    /**
     * 保存或更新个人分类
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleTag(ArticleBean bean, ArticleInfo article) {
        List<ArticleTagInfo> articleTags = article.getArticleTags();
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
            articleTagDao.update(tag);
        }
    }
}
