package cn.zznlin.simple.common.controller;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.SMDService;
import cn.zznlin.simple.base.service.UserService;
import cn.zznlin.simple.common.config.ViewName;
import cn.zznlin.simple.common.cons.AuthorCons;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhennan
 * @Date 2018/7/15 10:37
 * @Description
 */
public abstract class CommonController
{
    protected final String CLASS_NAME = getClass().getName();
    protected final String SIMPLE_CLASS_NAME = getClass().getSimpleName();


    protected abstract String getModule();
    protected String show = "show";
    protected String edit = "edit";
    protected String update = "update";
    // 是否发送异常邮件  true 发送
    private Boolean isSendMail = SystemPropertyInit.getInstance().getBooleanProperty("isSendCodeMail",true);

    protected String forward(ViewName viewName) {
        String path = this.getClass().getAnnotation(RequestMapping.class)
                .value()[0];
        LoggerUtils
                .debug(CLASS_NAME, "[CommonController] whole path ==> "
                        + getModule() + path.replaceAll("-", "") + path
                        + viewName.getValue());
        return getModule() + path.replaceAll("-", "") + path
                + viewName.getValue();
    }

    protected String redirect(String action) {
        return ViewName.redirect.getValue() + action;
    }

    protected String forward(String action) {
        return ViewName.forward.getValue() + action;
    }


    /**
     * 保存异常信息
     *
     * @param authorName
     * @param modelName
     * @param functionName
     * @param e
     */
    protected void saveException(AuthorCons authorName, String modelName,
                                 String functionName, Exception e) {
        try {
            // Exception的具体说明
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();

            String mailContent = StringUtils.getRandomCode()+"Language:[("
                    + SystemPropertyInit.getInstance().getProperty("server.host") + ")]"
                    + authorName.getName() + ":" + modelName + "==>" + functionName;
            e.printStackTrace();
            if(isSendMail){
                // 发送错误的邮件
//                mailSendUtil.sendSyncErrorMail("18519149312@163.com", mailContent, exception);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



    /**
     * 返回JSON结果集： data={"success":true|false,"message":"保存成功"|"保存失败"}
     * @param success
     * @param message
     */
    public void ajaxReturn(Boolean success,String message){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("message", message);
//		把对象转json字符串
        String jsonString = JSON.toJSONString(map);
//		回写到浏览器上
        HttpServletResponse response = getCurrentResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 shiro 中取出当前登录的用户
     * @return
     */
    public User getCurrentUser(){
        // 获取 程序与shiro交互的管理对象
        Subject subject = SecurityUtils.getSubject();
        // 获得主角 其实就是 realm 认证放入的User
        User user = (User)subject.getPrincipal();
        return user;
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> loop(List<T> list, List<T> nodes, int rows) {
        for (int i = 0; i < nodes.size(); i++) {
            T node = nodes.get(i);
            try {
                list.add(node);
                Method getChild = node.getClass().getMethod("getChild");
                List<T> childs = (List<T>) getChild.invoke(node);
                if (CollectionUtils.isNotEmpty(childs)) {
                    loop(list, childs, rows + 1);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return list;
    }

    /**
     * 获得当前请求
     * @return
     */
    public HttpServletRequest getCurrentRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return request;
    }

    /**
     * 获得当前请求的响应
     * @return
     */
    public HttpServletResponse getCurrentResponse(){
        HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
        return response;
    }

    @Autowired
    protected UserService userService;

    @Autowired
    protected SMDService smdService;

}

