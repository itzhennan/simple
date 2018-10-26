package cn.zznlin.simple.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @Author zhennan
 * @Date 2018/10/25 22:34
 * @Description
 */
public class RegexUtils {
    private static final String CLASS_NAME = JsonUtils.class.getName();
    private static final String REGEX_1 = "[/simple]?.*/js/.*";
    private static final String REGEX_2 = "[/simple]?.*/css/.*";
    private static final String REGEX_3 = "[/simple]?.*/images/.*";

    private RegexUtils(){
    }

    private static class RegexUtilsHolder{
        private static RegexUtils instance = new RegexUtils();
    }

    public static synchronized RegexUtils getInstance(){
        return RegexUtilsHolder.instance;
    }

    /**
     * 是否匹配系统静态资源目录 js css image
     * @param excue 待匹配文本
     * @return
     */
    public boolean isSystemPath(String excue){
        return isRegex(excue,REGEX_1) || isRegex(excue,REGEX_2) || isRegex(excue,REGEX_3);
    }

    /**
     *
     * @param excue  待匹配的字符串
     * @param regex  正则表达式
     * @return
     */
    private boolean isRegex(String excue,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(excue);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }

}
