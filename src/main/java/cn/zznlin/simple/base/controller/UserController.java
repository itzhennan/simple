package cn.zznlin.simple.base.controller;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.pojo.UserBean;
import cn.zznlin.simple.common.bean.LoginInfo;
import cn.zznlin.simple.common.bean.ReturnBaseJson;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.exception.pojo.Code404Exception;
import cn.zznlin.simple.common.utils.DateUtils;
import cn.zznlin.simple.common.utils.ShiroMD5Utils;
import cn.zznlin.simple.common.utils.StringUtils;
import cn.zznlin.simple.common.utils.ValidateUtils;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:55
 * @Description
 */
@Controller
@RequestMapping("/user")
public class UserController extends CommonController{

    /**
     * 用户登录接口
     * @param session
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnBaseJson login(@RequestBody UserBean userBean, HttpServletRequest request, HttpSession session){
        ReturnBaseJson returnBaseJson = new ReturnBaseJson();
        try {
//            String reqJson = HttpUtils.getReqJson(request);
//            LoggerUtils.debug(SIMPLE_CLASS_NAME,reqJson);
//            UserBean userBean = JsonUtils.fromJson(reqJson, UserBean.class);

            //		1、创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userBean.getName(),userBean.getPassword());
            //		2、获取主题
            Subject subject = SecurityUtils.getSubject();
            //		3、开始认证
            subject.login(token);
        } catch (AuthenticationException e) {
            returnBaseJson.setCode(1);
            returnBaseJson.setMsg("用户名或密码错误");
        }finally {
            return returnBaseJson;
        }
    }

    /**
     * 用户注册接口
     */
//    @RequestMapping("/register")
//    @ResponseBody
//    public ReturnBaseJson register(UserBean bean){
//        ReturnBaseJson returnBaseJson = new ReturnBaseJson();
//        try {
//            //密码加密并set
//            User user = new User();
//            user.setPassword(ShiroMD5Utils.SysMd5(bean));
//            userService.save(user);//将用户数据插入数据库
//        } catch (AuthenticationException e) {
//            returnBaseJson.setCode(1);
//            returnBaseJson.setMsg("数据异常");
//            e.printStackTrace();
//        }finally {
//            return returnBaseJson;
//        }
//    }

    /**
     * 获得用户密码
     */
    @RequestMapping(value = "/getShiroMD5/{name}/{pwd}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnBaseJson getShiroMD5(@PathVariable("name") String name, @PathVariable("pwd") String pwd){
        ReturnBaseJson returnBaseJson = new ReturnBaseJson();
        try {
            String sysMd5 = ShiroMD5Utils.SysMd5(new UserBean(name, pwd));
            returnBaseJson.setMsg(sysMd5);
        } catch (AuthenticationException e) {
            returnBaseJson.setCode(1);
            returnBaseJson.setMsg("数据异常");
        }finally {
            return returnBaseJson;
        }
    }

    /**
     * 获得当前登录的用户信息
     */
    @RequestMapping(value = "/loginInfo",method = RequestMethod.GET)
    @ResponseBody
    public ReturnBaseJson getCurrentUser(ReturnBaseJson returnBaseJson,HttpServletRequest request){
        try {
            Object principal = SecurityUtils.getSubject().getPrincipal();
            if(ValidateUtils.isEmpty(principal)){
                throw new Code404Exception();
            }
            LoginInfo login = (LoginInfo) principal;
            User user = userService.get(login.getId());

            HashMap<String,Object> map = Maps.newHashMap();
            map.put("name",user.getUserName());

            ServletContext servletContext = getServletContext();
            String imgServer = StringUtils.getStr(servletContext.getAttribute("imgServer"));
            map.put("url",imgServer + user.getUploadFiles().getFilePath());

//            String ipAddr = IPAddrUtils.getIpAddr(request);
//            TaobaoData cityDate4TB = IPAddrUtils.getCityDate4TB(ipAddr);
            map.put("address","登录时间："+ DateUtils.longToString(System.currentTimeMillis()));

            returnBaseJson.setData(map);
        } catch (AuthenticationException e) {
            returnBaseJson.setCode(1);
            returnBaseJson.setMsg("数据异常");
        }finally {
            return returnBaseJson;
        }
    }

    @Override
    protected String getModule() {
        return null;
    }
}
