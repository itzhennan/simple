package cn.zznlin.simple.common.senum;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhennan
 * @Date 2018/11/30 21:37
 * @Description
 */
public enum HttpExceptionEnum {
    NOT_FOUND_EXCEPTION(HttpServletResponse.SC_NOT_FOUND,"访问的页面不存在！"),
    NOT_SUPPORTED_METHOD_EXCEPTION(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"请求类型不支持"),
    NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,"请求媒体类型不支持"),
    NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION(HttpServletResponse.SC_NOT_ACCEPTABLE,"请求媒体类型被拒绝"),
    MISSING_REQUEST_PARAMETER_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，缺少必须参数"),
    REQUEST_BINDING_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，请求绑定失败"),
    NOT_SUPPORTED_CONVERSION_EXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"服务器内部错误"),
    TYPE_MISMATCH_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，类型转换异常"),
    MESSAGE_NOT_READABLE_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，消息读取异常"),
    MESSAGE_NOT_WRITABLE_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，消息写入异常"),
    NOT_VALID_METHOD_ARGUMENT_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，无效的方法参数"),
    MISSING_REQUEST_PART_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，缺少重要参数"),
    BIND_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST,"请求参数错误，方法绑定失败"),
    MISSING_PATH_VARIABLE_EXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误");


    /**
     * 异常代码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    HttpExceptionEnum( int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
