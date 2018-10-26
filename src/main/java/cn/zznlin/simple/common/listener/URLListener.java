package cn.zznlin.simple.common.listener;

import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.RegexUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 *
 * @author zhennan zhang
 * @email  itzhennan@163.com
 * @date   2018年8月15日 下午4:48:45
 *
 * 类说明 :
 *       URL 路径请求监听器
 */
@Component
public class URLListener implements ApplicationListener<ServletRequestHandledEvent> {

    private static final String SIMPLE_CLASS_NAME = URLListener.class.getName();

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {

        String requestUrl = event.getRequestUrl();
        RegexUtils regexUtils = RegexUtils.getInstance();
        if(!regexUtils.isSystemPath(requestUrl)){
            LoggerUtils.debug(SIMPLE_CLASS_NAME, requestUrl);
//            LoggerUtils.debug(SIMPLE_CLASS_NAME, event.getDescription());
        }
    }
}
