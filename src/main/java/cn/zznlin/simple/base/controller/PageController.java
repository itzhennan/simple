package cn.zznlin.simple.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhennan
 * @Date 2018/12/17 21:21
 * @Description
 */
@Controller
public class PageController {


    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }

}
