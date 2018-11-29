package cn.zznlin.simple.common.utils;

import com.google.common.collect.Lists;

import java.util.List;
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
    private static final String REGEX_4 = "[/simple]?.*/fonts/.*";
    private static final String REGEX_5 = "[/simple]?.*/admin/assets/.*";

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
        return isRegex(excue,REGEX_1) || isRegex(excue,REGEX_2) || isRegex(excue,REGEX_3) || isRegex(excue,REGEX_4) || isRegex(excue,REGEX_5);
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

    /**
     * 获得原字符串中所有的src
     * @param content  带匹配字符串
     * @return
     */
    public static List<String> getImgSrcList(String content){
        // 获得所有的img
        List<String> imgList = getImgList(content);
        List<String> srcList = Lists.newArrayList();
        for (String imgStr:imgList) {
            // 获得img中的src
            String srcStr = getImgSrc(imgStr);
            if(srcStr != null){
                srcList.add(srcStr);
            }
        }
        return srcList;
    }

    /**
     * 获得目标串中的所有的 img标签
     * @param content  带匹配字符串
     * @return
     */
    public static List<String> getImgList(String content){
        List<String> list = Lists.newArrayList();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/> <img alt="" src="1.jpg"></img> <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);
                list.add(str_img);
                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }

    /**
     * 获得Image标签中的src
     * @param content  带匹配字符串
     * @return
     */
    public static String getImgSrc(String content){
        //开始匹配<img />标签中的src
        Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
        Matcher m_src = p_src.matcher(content);
        if (m_src.find()) {
            return m_src.group(3);
        }
        return null;
    }



}
