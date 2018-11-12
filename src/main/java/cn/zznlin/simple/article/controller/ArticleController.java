package cn.zznlin.simple.article.controller;

import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhennan
 * @Date 2018/10/26 22:39
 * @Description
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends CommonController {

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response){
        return "/article/article-edit";
    }

    @RequestMapping("/articleId")
    public String detail(@PathVariable String articleId, HttpServletRequest request, HttpServletResponse response){
        return "/article/article-detail";
    }

    @RequestMapping("/postedit/saveArticle")
    public void postSaveArticle(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "0") int isPub) throws Exception {
        String reqJson = HttpUtils.getReqJson(request);




    }

    @Override
    protected String getModule() {
        return null;
    }
}
