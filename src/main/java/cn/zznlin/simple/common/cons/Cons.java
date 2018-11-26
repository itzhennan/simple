package cn.zznlin.simple.common.cons;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:38
 * @Description
 */
public interface Cons {
    /**用户SESSION**/
    String ZZNLIN_SESSION_BEAN = "ZZNLIN_SESSION_BEAN";

    /** 项目相关 **/
    String SIMPLE = "simple";

    /**常用String**/
    // 空
    String EMPTY = "";
    // 下划线
    String UNDERLINE = "_";
    // 斜线
    String SLASH = "/";
    // 反斜线
    String BACKSLASH = "\\";

    /**正则**/
    String CARRIAGE ="\\s*|\t|\r|\n";//空格，回车，制表符正则表达式

    /**
     * 数据库相关
     */
    // 实体类名 头部 simple_
    String TABLEHEAD = SIMPLE + UNDERLINE;

    // 主键增长名称  simple_generator
    String GENERATOR = SIMPLE + UNDERLINE + "generator";

    // 主键 自动增长  适用于mysql
    String IDENTITY = "identity";
    // 主键 自动根据数据库调整
    String NATIVE = "native";
    // 主键增长选择
    String STRATEGY = NATIVE;

}
