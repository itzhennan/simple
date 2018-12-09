package cn.zznlin.simple.common.bean;

import java.io.Serializable;

/**
 * @author zhennan zhang
 * @date 2018/12/7 15:19
 * @description
 */
public class LoginInfo implements Serializable {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
