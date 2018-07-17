package cn.zznlin.simple.base.pojo;

import cn.zznlin.simple.base.entity.User;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:45
 * @Description
 */
public class SessionBean {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
