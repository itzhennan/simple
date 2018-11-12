package cn.zznlin.simple.base.controller;

import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.base.service.SMDService;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.utils.HttpUtils;
import cn.zznlin.simple.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:55
 * @Description
 */
@Controller
@RequestMapping("/smd")
public class SMDController extends CommonController{

    @Autowired
    private SMDService smdService;

    @RequestMapping("/postSaveSmd")
    @ResponseBody
    public String postSaveSmd() throws Exception {
        String reqJson = HttpUtils.getReqJson();
        SMDInfo smd = JsonUtils.fromJson(reqJson, SMDInfo.class);
        smdService.save(smd);
        return "";
    }
    @Override
    protected String getModule() {
        return null;
    }
}
