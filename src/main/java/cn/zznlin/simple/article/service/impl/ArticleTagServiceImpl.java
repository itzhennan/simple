package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.dao.ArticleCategoryMapperDao;
import cn.zznlin.simple.article.dao.ArticleTagDao;
import cn.zznlin.simple.article.dao.ArticleTagMapperDao;
import cn.zznlin.simple.article.entity.ArticleCategoryMapperInfo;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.article.entity.ArticleTagMapperInfo;
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

    @Autowired
    private ArticleTagMapperDao articleTagMapperDao;



    /**
     * 保存或更新个人分类
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleTag(ArticleBean bean, ArticleInfo article) {
        // 拿到未被删除的
        List<ArticleTagMapperInfo> articleMapperTags = article.getArticleMapperTags();

        if(articleMapperTags == null){
            articleMapperTags = Lists.newArrayList();
        }
        // 暂时全部置1
        HashMap<String,ArticleTagMapperInfo> tagMaps = Maps.newHashMap();
        for (ArticleTagMapperInfo tagMapper :articleMapperTags ) {
            tagMapper.setIsDel(1);
            tagMaps.put(tagMapper.getTag().getTagName(), tagMapper);
        }

        // 新增的保存。  以前存在的，现在还存在的，修改del
        String[] tags = bean.getTag2().split(",");
        for (String tag: tags) {
            ArticleTagMapperInfo articleTagMapper = tagMaps.get(tag);
            if(ValidateUtils.isNotEmpty(articleTagMapper)){
                articleTagMapper.setIsDel(0);
            }else{
                ArticleTagInfo tempTag = new ArticleTagInfo();
                tempTag.setTagName(tag);
                articleTagDao.save(tempTag);

                ArticleTagMapperInfo articleTagMapperInfo = new ArticleTagMapperInfo();
                articleTagMapperInfo.setTag(tempTag);
                articleTagMapperInfo.setArticle(article);
                articleTagMapperDao.save(articleTagMapperInfo);
            }
        }
        // 更新删除的数据
        for (ArticleTagMapperInfo tag :articleMapperTags ) {
            if(tag.getIsDel() == 1){
                articleTagMapperDao.update(tag);
            }
        }
    }
}
