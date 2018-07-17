package cn.zznlin.simple.common.config;

/**
 * @Author zhennan
 * @Date 2018/7/15 10:46
 * @Description
 */
public enum  ViewName {

    list("-list"), // web list
    mlist("-mlist"), // mobile list
    mitem("-mitem"), // mobile items
    insert("-insert"),
    show("-show"), // web show
    mshow("-mshow"), // mobile show
    edit("-edit"),
    info("-info"),
    valid("-valid"),
    roleEdit("-roleEdit"),
    resourceEdit("-resourceEdit"),
    redirect("redirect:"),
    forward("forward:");

    private String value;

    private ViewName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
