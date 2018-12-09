package cn.zznlin.simple.common.exception.pojo;

/**
 * @Author zhennan
 * @Date 2018/11/30 20:33
 * @Description
 *      404 异常类 代码抛出404异常会跳到404页面
 */
public class Code404Exception extends BaseException {

    public Code404Exception() {
    }

    public Code404Exception(String message) {
        super(message);
    }
}
