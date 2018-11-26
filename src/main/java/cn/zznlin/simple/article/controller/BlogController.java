package cn.zznlin.simple.article.controller;

import cn.zznlin.simple.article.entity.ArticleInfo;
import cn.zznlin.simple.article.pojo.ArticleCond;
import cn.zznlin.simple.article.service.ArticleService;
import cn.zznlin.simple.common.bean.Page;
import cn.zznlin.simple.common.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhennan
 * @Date 2018/11/21 22:13
 * @Description
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends CommonController {

    @Autowired
    private ArticleService articleService;

    /**
     *  博客首页
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model,Page page,ArticleCond cond){
        articleService.findPublicIndex(cond,page);
        return "/article/article-list";
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


    @Override
    protected String getModule() {
        return null;
    }
}
