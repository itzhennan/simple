package cn.zznlin.simple.author.controller;

import cn.zznlin.simple.common.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhennan
 * @Date 2018/7/22 14:36
 * @Description
 */
@Controller
@RequestMapping("/author")
public class AuthorController extends CommonController{

    @RequestMapping("/{authorId}")
    public String authorIndex(@PathVariable String authorId, HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("authorId",authorId);
        return "/author/index";
    }

    @Override
    protected String getModule() {
        return null;
    }
}
