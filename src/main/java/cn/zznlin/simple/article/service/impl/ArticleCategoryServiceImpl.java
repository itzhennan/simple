package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.dao.ArticleCategoryDao;
import cn.zznlin.simple.article.dao.ArticleCategoryMapperDao;
import cn.zznlin.simple.article.dao.ArticleTagMapperDao;
import cn.zznlin.simple.article.entity.*;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleCategoryService;
import cn.zznlin.simple.base.entity.User;
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

    @Autowired
    private ArticleCategoryMapperDao articleCategoryMapperDao;


    /**
     * 保存或更新文章标签
     * @param bean
     * @param article
     */
    @Override
    public void saveOrUpdateArticleCategory(ArticleBean bean, ArticleInfo article) {
        // 拿到未被删除的
        List<ArticleCategoryMapperInfo> articleMapperCategorys = article.getArticleMapperCategorys();

        if(articleMapperCategorys == null){
            articleMapperCategorys = Lists.newArrayList();
        }
        // 暂时全部置1
        HashMap<String,ArticleCategoryMapperInfo> categoryMaps = Maps.newHashMap();
        for (ArticleCategoryMapperInfo categoryMapper :articleMapperCategorys ) {
            categoryMapper.setIsDel(1);
            categoryMaps.put(categoryMapper.getCategory().getCategoryName(), categoryMapper);
        }

        // 新增的保存。  以前存在的，现在还存在的，修改del
        String[] categorys = bean.getTag2().split(",");
        for (String category: categorys) {
            ArticleCategoryMapperInfo articleCategoryMapper = categoryMaps.get(category);
            if(ValidateUtils.isNotEmpty(articleCategoryMapper)){
                articleCategoryMapper.setIsDel(0);
            }else{
                ArticleCategoryInfo tempCategory = new ArticleCategoryInfo();
                tempCategory.setCategoryName(category);
                articleCategoryDao.save(tempCategory);

                ArticleCategoryMapperInfo articleCategoryMapperInfo = new ArticleCategoryMapperInfo();
                articleCategoryMapperInfo.setCategory(tempCategory);
                articleCategoryMapperInfo.setArticle(article);
                articleCategoryMapperDao.save(articleCategoryMapperInfo);
            }
        }
        // 更新删除的数据
        for (ArticleCategoryMapperInfo category :articleMapperCategorys ) {
            if(category.getIsDel() == 1){
                articleCategoryMapperDao.update(category);
            }
        }

    }

    /**
     * 获得用户的所有的个人标签
     * @param user
     * @return
     */
    @Override
    public List<ArticleCategoryInfo> findAllByUser(User user) {
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("user", user);
        map.put("isDel", 0);
        return articleCategoryDao.findDatas(map);
    }
}
