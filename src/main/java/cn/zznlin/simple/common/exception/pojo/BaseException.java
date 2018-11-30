package cn.zznlin.simple.common.exception.pojo;

import java.io.Serializable;

/**
 * @author zhennan zhang
 * @date 2018/11/30 15:40
 * @description
 *
 *
 *
 */
public class BaseException extends RuntimeException implements Serializable {

    //异常信息
    protected String message;

    public BaseException() {
    }

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
