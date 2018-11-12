package cn.zznlin.simple.common.cons;

/**
 * @author zhennan zhang
 * @date 2018/11/12 17:58
 * @description
 */
public enum AuthorCons {
    zhangzhennan("张振楠");

    String name;

    AuthorCons(String name){
        this.name = name;
    }

    /**
     * @return the status
     */
    public String getName() {
        return name;
    }
}
