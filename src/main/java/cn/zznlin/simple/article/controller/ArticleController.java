package cn.zznlin.simple.article.controller;

import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.entity.ArticleTagInfo;
import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleService;
import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.bean.ReturnJsonBean;
import cn.zznlin.simple.common.cons.AuthorCons;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.helper.JSONHelper;
import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/10/26 22:39
 * @Description
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends CommonController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String save(HttpServletRequest request, HttpServletResponse response, Model model){
        List<SMDInfo> categorys = smdService.findDatas("type", 2);
        model.addAttribute("categorys", categorys);
        return "/article/article-edit";
    }

    /**
     * 修改文章
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/edit/{articleId}")
    public String edit(@PathVariable String articleId,HttpServletRequest request, HttpServletResponse response, Model model){
        List<SMDInfo> categorys = smdService.findDatas("type", 2);
        model.addAttribute("categorys", categorys);

        ArticleInfo bean =  articleService.get(articleId);
        model.addAttribute("bean",bean);

        // 文章标签
        List<ArticleTagInfo> articleTags = bean.getArticleTags();
        List<String> tagList = Lists.newArrayList();
        for (ArticleTagInfo tag:articleTags) {
            tagList.add(tag.getTagName());
        }
        String tagStr = StringUtils.join(tagList, ",");
        model.addAttribute("tagStr",tagStr);

        // 个人分类
        List<ArticleCategoryInfo> articleCategorys = bean.getArticleCategorys();
        List<String> catList = Lists.newArrayList();
        for (ArticleCategoryInfo cat:articleCategorys) {
            catList.add(cat.getCategoryName());
        }
        String catStr = StringUtils.join(catList, ",");
        model.addAttribute("catStr",catStr);

        return "/article/article-edit";
    }

    /**
     * 查看文章
     * @param articleId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/{articleId}")
    public String detail(@PathVariable String articleId, HttpServletRequest request, HttpServletResponse response, Model model){
        ArticleInfo bean =  articleService.get(articleId);
        model.addAttribute("bean",bean);
        return "/article/article-detail";
    }

    @RequestMapping("/saveArticle")
    @ResponseBody
    public ReturnJsonBean postSaveArticle(HttpServletRequest request, HttpServletResponse response,
                                          ArticleBean bean, @RequestParam(defaultValue = "0") int isPub) throws Exception {
        ReturnJsonBean returnBean = new ReturnJsonBean();
        try {
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(bean));
            User user = userDao.get(1L);
            articleService.saveOrUpdateArticle(user,bean,isPub);

        }catch (Exception e){
            returnBean.setCode(2);
            saveException(AuthorCons.zhangzhennan,SIMPLE_CLASS_NAME ,"postSaveArticle" , e);
        }finally {
            return returnBean;
        }
    }

    @Override
    protected String getModule() {
        return null;
    }
}
