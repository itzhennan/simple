package cn.zznlin.simple.base.pojo;

import cn.zznlin.simple.base.entity.User;

import java.io.Serializable;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:34
 * @Description 登录验证类
 */
public class ValidateResult implements Serializable {
    private int status = 0; //0正常  1错误
    private User currentUser = null;
    // 跳转路径
    private String url;

    public ValidateResult() {
    }

    public ValidateResult(User currentUser) {
        this.currentUser = currentUser;
    }

    public ValidateResult(int status, User currentUser) {
        this.status = status;
        this.currentUser = currentUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
