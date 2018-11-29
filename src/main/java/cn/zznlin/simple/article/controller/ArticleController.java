package cn.zznlin.simple.article.controller;

import cn.zznlin.simple.article.pojo.ArticleBean;
import cn.zznlin.simple.article.service.ArticleCategoryService;
import cn.zznlin.simple.article.service.ArticleService;
import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.bean.ReturnListJson;
import cn.zznlin.simple.common.cons.AuthorCons;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.helper.JSONHelper;
import cn.zznlin.simple.common.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private ArticleCategoryService articleCategoryService;


    /**
     * 添加
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String save(HttpServletRequest request, HttpServletResponse response, Model model){
        List<SMDInfo> blogCategorys = smdService.findDatas("type", 2);
        model.addAttribute("blogCategorys", blogCategorys);
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
        User user = userService.get(1L);
        articleService.getEditById(articleId,model,user);
        return "/article/article-edit";
    }

    @RequestMapping("/saveArticle")
    @ResponseBody
    public ReturnListJson postSaveArticle(HttpServletRequest request, HttpServletResponse response,
                                          ArticleBean bean, @RequestParam(defaultValue = "0") int isPub) throws Exception {
        ReturnListJson returnBean = new ReturnListJson();
        try {
            LoggerUtils.debug(SIMPLE_CLASS_NAME, "请求数据："+ JSONHelper.toJson(bean));
            User user = userService.get(1L);
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
