package cn.zznlin.simple.article.service.impl;

import cn.zznlin.simple.article.dao.ArticleDao;
import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.article.entity.ArticleCategoryMapperInfo;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagMapperInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.pojo.ArticleCond;
import cn.zznlin.simple.article.service.ArticleCategoryService;
import cn.zznlin.simple.article.service.ArticleService;
import cn.zznlin.simple.article.service.ArticleTagService;
import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.SMDService;
import cn.zznlin.simple.common.bean.Page;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.HtmlUtils;
import cn.zznlin.simple.common.utils.ReflectionUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import cn.zznlin.simple.common.utils.ValidateUtils;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author zhennan zhang
 * @date 2018/11/12 15:28
 * @description
 */
@Service
public class ArticleServiceImpl extends HibernateServiceSupport<ArticleInfo> implements ArticleService {

    @Autowired
    private SMDService smdService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleDao articleDao;

    /**
     * 保存或修改文章
     * @param user
     * @param bean
     * @param isPub
     */
    @Override
    public void saveOrUpdateArticle(User user ,ArticleBean bean, int isPub) throws UnsupportedEncodingException {
        Long artid = bean.getArtid();
        ArticleInfo article = null;
        if(artid == 0){
            article = new ArticleInfo();
            article.setUser(user);
        }else{
            article = articleDao.get(artid);
        }

        article.setTitle(bean.getTitl());
        article.setCont(bean.getCont());
        // 文章类型
        if(bean.getTyp() != 0){
            article.setType(smdService.get(bean.getTyp()));
        }
        // 博客分类
        if(bean.getChnl() != 0){
            article.setBlogCategory(smdService.get(bean.getChnl()));
        }
        // 私密文章
        article.setIsPrivate(bean.getPrivate() ? 1:0);
        // 文章状态 是否发布
        boolean publish = "publish".equals(bean.getStat());

        // 发布时间  第一次更改为发布的
        if(publish && article.getStatus() == 0){
            DateTime publishDateTime = new DateTime();
            article.setPublicDateTime(publishDateTime);
            // 生成摘要
            if(StringUtils.isEmpty(bean.getAbstracts())){
                String abstracts = autoAbstracts(bean.getCont());
                article.setAbstracts(abstracts+"...");
            }
        }
        // 发布状态
        article.setStatus( publish ? 1:0);

        articleDao.saveOrUpdate(article);

        //文章标签
        if(StringUtils.isNoEmpty(bean.getTag2())){
            articleTagService.saveOrUpdateArticleTag(bean, article);
        }

        // 个人分类
        if(StringUtils.isNoEmpty(bean.getCategories())){
            articleCategoryService.saveOrUpdateArticleCategory(bean, article);
        }
    }

    private String autoAbstracts(String cont) throws UnsupportedEncodingException {
        return HtmlUtils.extractContent(cont,100);
    }

    @Override
    public ArticleInfo get(String articleId) {
        if(StringUtils.isEmpty(articleId)){
            return null;
        }
        ArticleInfo articleInfo = articleDao.get(StringUtils.getLong(articleId));
        if(ValidateUtils.isNotEmpty(articleInfo)){
            return articleInfo;
        }else{
            return null;
        }
    }

    // 获得公开个人博客首页的信息 无须权限登录
    @Override
    public List<ArticleBean> findPublicIndex(ArticleCond cond, Page page) {
        cond.setIsDel(0);
        cond.setIsPrivate(0);
        cond.setStatus(1);
        return findIndex(cond,page);
    }

    // 获得私人个人博客首页的信息 必须登录
    @Override
    public List<ArticleBean> findPrivateIndex(ArticleCond cond, Page page) {
        cond.setIsDel(0);
        return findIndex(cond,page);
    }

    // 获得需要修改的文章信息
    @Override
    public void getEditById(String articleId,Model model,User user) {
        // 博客分类
        List<SMDInfo> blogCategorys = smdService.findDatas("type", 2);
        model.addAttribute("blogCategorys", blogCategorys);

        // 个人分类
        List<ArticleCategoryInfo> categorys =  articleCategoryService.findAllByUser(user);
        model.addAttribute("categorys",categorys);

        // 文章对象
        ArticleInfo bean =  get(articleId);
        // 文章标签
        String tagStr = "";
        List<ArticleTagMapperInfo> articleMapperTags = bean.getArticleMapperTags();
        if(ValidateUtils.isNotEmpty(articleMapperTags)){
            List<String> tagList = Lists.newArrayList();
            for (ArticleTagMapperInfo tagMapper: articleMapperTags) {
                tagList.add(tagMapper.getTag().getTagName());
            }
            tagStr = StringUtils.join(tagList, ",");
        }


        // 个人分类
        String catStr = "";
        List<ArticleCategoryMapperInfo> articleMapperCategorys = bean.getArticleMapperCategorys();
        if(ValidateUtils.isNotEmpty(articleMapperCategorys)){
            List<String> catList = Lists.newArrayList();
            for (ArticleCategoryMapperInfo catMapper:articleMapperCategorys) {
                catList.add(catMapper.getCategory().getCategoryName());
            }
            catStr = StringUtils.join(catList, ",");
        }

        ArticleBean articleBean = new ArticleBean();
        articleBean.setArtid(bean.getArticleId());
        articleBean.setFileName(bean.getArticleId().toString());
        articleBean.setTitl(bean.getTitle());
        articleBean.setStat(bean.getStatus().toString());
        articleBean.setTag2(tagStr);
        articleBean.setCategories(catStr);
        articleBean.setChnl(bean.getBlogCategory() != null  ? bean.getBlogCategory().getSmdId() : 0L);
        articleBean.setTyp(bean.getType() != null  ? bean.getType().getSmdId() : 0L);
        articleBean.setCont(bean.getCont());

        model.addAttribute("articleBean",articleBean);
    }


    public List<ArticleBean> findIndex(ArticleCond cond,Page page) {
        List<ArticleBean> datas = Lists.newArrayList();
        List<Map<String, Object>> pageByNative = articleDao.findPageByNative(cond.findIndex(), page);
        for (Map<String, Object> map:pageByNative) {
            datas.add(ReflectionUtils.fillClassForMap(map,ArticleBean.class));
        }
        return datas;
    }

}
