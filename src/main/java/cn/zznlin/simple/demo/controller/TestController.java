package cn.zznlin.simple.demo.controller;

import cn.zznlin.simple.common.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhennan
 * @Date 2018/7/25 20:34
 * @Description
 */
@Controller
@RequestMapping("/test")
public class TestController extends CommonController {


    //
    @RequestMapping("/test1")
    public String test(){
        return "/test/test";
    }


    @Override
    protected String getModule() {
        return null;
    }
}
