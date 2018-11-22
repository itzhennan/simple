package cn.zznlin.simple.base.controller;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.pojo.SessionBean;
import cn.zznlin.simple.common.cons.Cons;
import cn.zznlin.simple.common.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:55
 * @Description
 */
@Controller
@RequestMapping("/admin")
public class UserController extends CommonController{

    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpSession session){
        String password = user.getPassword();
        String userPhone = user.getUserPhone();
        List<User> userLists = userService.findDatas("userPhone", userPhone);
        if(userLists != null && userLists.size() > 0){
            User currentUser = userLists.get(0);
            if(currentUser.getPassword().equals(password)){
                SessionBean sessionBean = new SessionBean();
                sessionBean.setCurrentUser(currentUser);
                session.setAttribute(Cons.ZZNLIN_SESSION_BEAN,sessionBean);
            }
        }
        return "content";
    }

    @Override
    protected String getModule() {
        return null;
    }
}
