package cn.zznlin.simple.common.exception.pojo;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * @author zhennan zhang
 * @date 2018/11/30 15:43
 * @description
 */
public class DefaultException extends BaseException {

    public DefaultException() {
    }

    public DefaultException(String message) {
        super(message);
    }
}
