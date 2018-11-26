package cn.zznlin.simple.base.controller;

import cn.zznlin.simple.base.pojo.UserBean;
import cn.zznlin.simple.common.controller.CommonController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
     * @param userBean
     * @param session
     */
    @RequestMapping("/login")
    public void login(UserBean userBean, HttpSession session){
        //		1、创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getName(),userBean.getPassword());
        //		2、获取主题
        Subject subject = SecurityUtils.getSubject();
        //		3、开始认证
        try {
            subject.login(token);
            ajaxReturn(true, "");
        } catch (AuthenticationException e) {
            ajaxReturn(false, "用户名或密码错误");
            e.printStackTrace();
        }
    }

    /**
     * 用户退出登录接口
     */
    @RequestMapping("/logout")
    public void logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();

            ajaxReturn(true, "");
        } catch (AuthenticationException e) {
            ajaxReturn(false, "用户退出登录失败！");
            e.printStackTrace();
        }
    }

    @Override
    protected String getModule() {
        return null;
    }
}
