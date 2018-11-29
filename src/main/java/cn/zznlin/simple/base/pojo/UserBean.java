package cn.zznlin.simple.base.pojo;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:10
 * @Description
 */
public class UserBean {

    private String name;
    private String password;

    public UserBean() {
    }

    public UserBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
