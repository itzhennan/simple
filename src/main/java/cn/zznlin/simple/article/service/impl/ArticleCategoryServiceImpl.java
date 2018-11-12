package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleCategoryService;
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
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Resource(name = "ArticleCategoryDao")
    private BaseDao<ArticleCategoryInfo> articleCategoryDao;

    /**
     * 保存或更新文章标签
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleCategory(ArticleBean bean, ArticleInfo article) {
        List<ArticleCategoryInfo> articleCategorys = article.getArticleCategorys();
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
            articleCategoryDao.update(cat);
        }

    }
}
