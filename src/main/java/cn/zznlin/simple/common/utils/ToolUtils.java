package cn.zznlin.simple.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhennan
 * @Date 2018/10/28 23:44
 * @Description
 */
public class ToolUtils {
    private static final Logger logger = LoggerFactory.getLogger(ToolUtils.class);

    /**
     * 取得request对象
     *
     * @return
     *
     * @throws
     * @author fangsj
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
