package cn.zznlin.simple.common.bean;

/**
 * @author zhennan zhang
 * @date 2018/11/29 17:50
 * @description
 */
public class ReturnBaseJson {
    public static final String MSG_SUCCES = "OK";
    public static final int CODE_SUCCESS = 0; // 正常
    public static final int CODE_FAIL = 1; // 参数有误
    public static final int CODE_LIMIT = 2; // 未登陆

    protected int code = CODE_SUCCESS;
    protected String msg;

    public ReturnBaseJson(){
        msg = MSG_SUCCES;
        code = CODE_SUCCESS;
    }

    public ReturnBaseJson(int code,String msg){
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
