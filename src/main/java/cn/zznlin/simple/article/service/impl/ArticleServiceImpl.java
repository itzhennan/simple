package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleCategoryService;
import cn.zznlin.simple.article.service.ArticleService;
import cn.zznlin.simple.article.service.ArticleTagService;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.SMDService;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhennan zhang
 * @date 2018/11/12 15:28
 * @description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private SMDService smdService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Resource(name = "ArticleDao")
    private BaseDao<ArticleInfo> articleDao;


    /**
     * 保存或修改文章
     * @param user
     * @param bean
     * @param isPub
     */
    @Override
    public void saveOrUpdateArticle(User user ,ArticleBean bean, int isPub) {
        Long artid = bean.getArtid();
        ArticleInfo article = null;
        if(artid == 0){
            article = new ArticleInfo();
        }else{
            article = articleDao.get(artid);
        }
        article.setUser(user);
        article.setTitle(bean.getTitl());
        article.setCont(bean.getCont());
        // 文章类型
        article.setType(smdService.get(bean.getTyp()));
        // 博客分类
        article.setBlogCategory(smdService.get(bean.getChnl()));
        // 私密文章
        article.setIsPrivate(bean.getPrivate() ? 1:0);
        // 文章状态 是否发布
        boolean publish = "publish".equals(bean.getStat());

        // 发布时间  第一次更改为发布的
        if(publish && article.getStatus() == 0){
            DateTime publishDateTime = new DateTime();
            article.setPublicDateTime(publishDateTime);
        }
        article.setStatus( publish ? 1:0);
//        articleService.saveOrUpdate(article);
        if(artid == 0){
            articleDao.save(article);
        }else{
            articleDao.update(article);
        }

        //文章标签
        articleTagService.saveOrUpdateArticleTag(bean, article);

        // 个人分类
        articleCategoryService.saveOrUpdateArticleCategory(bean, article);
    }

}
