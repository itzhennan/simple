package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.common.init.SystemPropertyInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zhennan
 * @Date 2018/7/15 10:47
 * @Description  日志信息工具类
 */
public class LoggerUtils {

    private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class);
    public static boolean IS_LOG = SystemPropertyInit.getInstance().getBooleanProperty("isLog",true);
    public static boolean IS_OPEN = true;
    public static boolean IS_LINE = true;

    /**
     * DEBUG信息
     *
     * @param className
     * @param content
     */
    public static void debug(String className, String content){
        if(IS_LOG) {
            logger.debug("["+className+"] "+content);
        }
    }

    /**
     * DEBUG信息
     *
     * @param className
     * @param content
     */
    public static void debug(String className, String content, Object obj){
        if(IS_LOG) {
            logger.debug("["+className+"] "+content, obj);
        }
    }

    /**
     * INFO信息
     *
     * @param className
     * @param content
     */
    public static void info(String className, String content){
        if(IS_LOG) {
            logger.info("["+className+"] "+content);
        }
    }

    /**
     * ERROR信息
     *
     * @param className
     * @param content
     */
    public static void error(String className, String content){
        if(IS_LOG) {
            logger.error("["+className+"] "+content);
        }
    }

    /**
     * ERROR信息
     *
     * @param className
     * @param content
     */
    public static void error(String className, String content, Exception e){
        if(IS_LOG) {
            logger.error("["+className+"] "+content, e);
        }
    }

    /**
     * ERROR信息
     *
     * @param className
     * @param content
     */
    public static void error(String className, String content, String message){
        if(IS_LOG) {
            logger.error("["+className+"] "+content, message);
        }
    }

    /**
     * WARN信息
     *
     * @param className
     * @param content
     */
    public static void warn(String className, String content){
        if(IS_LOG) {
            logger.warn("["+className+"] "+content);
        }
    }
}
