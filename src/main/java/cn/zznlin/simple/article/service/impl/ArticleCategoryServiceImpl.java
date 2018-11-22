package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.dao.ArticleCategoryDao;
import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleCategoryService;
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
public class ArticleCategoryServiceImpl extends HibernateServiceSupport<ArticleCategoryInfo> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryDao articleCategoryDao;

    /**
     * 保存或更新文章标签
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleCategory(ArticleBean bean, ArticleInfo article) {
        // 拿到所有未被删除的
        List<ArticleCategoryInfo> articleCategorys = articleCategoryDao.getNowArticleCategorys(article.getArticleId());

        if(articleCategorys == null){
            articleCategorys = Lists.newArrayList();
        }
        HashMap<String,ArticleCategoryInfo> catMaps = Maps.newHashMap();
        for (ArticleCategoryInfo cat :articleCategorys ) {
            cat.setIsDel(1);
            catMaps.put(cat.getCategoryName(), cat);
        }
        String[] catgorys = bean.getCategories().split(",");
        for (String catgory: catgorys) {
            ArticleCategoryInfo categoryInfo = catMaps.get(catgory);
            if(ValidateUtils.isNotEmpty(categoryInfo)){
                categoryInfo.setIsDel(0);
            }else{
                categoryInfo = new ArticleCategoryInfo();
                categoryInfo.setCategoryName(catgory);
                categoryInfo.setArticle(article);
                articleCategoryDao.save(categoryInfo);
            }
        }
        for (ArticleCategoryInfo cat :articleCategorys ) {
            if(cat.getIsDel() == 0){
                articleCategoryDao.update(cat);
            }
        }

    }
}
