package cn.zznlin.simple.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zhennan
 * @Date 2018/7/15 11:07
 * @Description
 */
public class StringUtils {

    public static final String COMMA = ",";
    public static final String POINT = "\\.";
    public static final String BREAK = "\\n";
    public static final String SEMICOLON = "\\;";
    public static final String _COMMA = "\\,";
    public static final String FEMPTY = " ";
    public static final String CEMPTY = " ";
    public static final String UNDERLINE = "_";
    public static final String SINGLE_QUOTE = "'";
    public static final String P_SINGLE_QUOTE = "\\\\'";

    /**
     * 生成随机6位数字
     *
     * @return
     */
    public static String getRandomCode() {
        String code = "";
        while (code.length() < 6){
            code += (int) (Math.random() * 10);
        }

        return code;
    }

    /**
     * SQL模糊搜索字符串转义
     *
     * @param str
     * @return
     */
    public static String escapeSQLStr(String str) {
        return str.trim().replaceAll(SINGLE_QUOTE, P_SINGLE_QUOTE)
                .replaceAll(CEMPTY, "");
    }

    /**
     * 去空格
     *
     * @param str
     * @return
     */
    public static String strTrim(String str){
        return str.trim().replaceAll(CEMPTY, "").replaceAll(FEMPTY, "").replaceAll(BREAK, "");
    }

    /**
     * 去除字符串中所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     * @param s
     * @return
     */
    public static String removeAllBlank(String s){
        String result = "";
        if(null!=s && !"".equals(s)){
            result = s.replaceAll("[　*| *| *|//s*]*", "");
        }
        return result;
    }

    /**
     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     * @param s
     * @return
     */
    public static String trim(String s){
        String result = "";
        if(null!=s && !"".equals(s)){
            result = s.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
        }
        return result;
    }

    public static String replaceStr(String str, String target,
                                    String destination) {
        return str.replaceAll(target, destination);
    }

    public static List<String> stringTokenizer(String value) {
        List<String> results = Lists.newArrayList();
        StringTokenizer st = new StringTokenizer(value);
        while (st.hasMoreElements()) {
            String str = st.nextElement().toString();
            if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
                results.add(str);
            }
        }
        return results;
    }

    public static String collectionToString(Collection<Object> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Object obj : collection) {
                if (builder.length() != 0) {
                    builder.append(COMMA).append(String.valueOf(obj));
                } else {
                    builder.append(String.valueOf(obj));
                }
            }
            return builder.toString();
        }
    }

    public static Set<Long> stringToSet(String str) {
        Set<Long> result = Sets.newHashSet();
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return result;
        }
        for (String value : str.split(COMMA)) {
            result.add(Long.valueOf(value));
        }
        return result;
    }

    public static Set<Long> stringToSet(String[] values) {
        Set<Long> result = Sets.newHashSet();
        if (values != null) {
            for (String value : values) {
                result.add(Long.valueOf(value));
            }
        }
        return result;
    }

    /**
     *
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (isNoEmpty(content)) {
            try {
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return size;
    }

    /**
     * String to List with special character
     *
     * @param arrayStr
     * @param special
     * @return
     */
    public static List<String> getListWithSpecial(String arrayStr,
                                                  String special) {
        List<String> array = new ArrayList<String>();
        if (isNoEmpty(arrayStr)) {
            String[] arrays = arrayStr.split(special);
            array = Arrays.asList(arrays);
        }

        return array;
    }

    public static boolean isNoEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        }

        return false;
    }

    public static String getString(String str) {
        if (isNoEmpty(str)) {
            return str;
        }

        return "";
    }

    /**
     * 获得前一篇文章ID
     *
     * @param ids
     * @param curentId
     * @return
     */
    public static long getLastId(List<Long> ids, long curentId) {
        long lastId = 0;

        if (ids.size() > 0) {
            for (Long id : ids) {
                if (id < curentId) {
                    lastId = id;
                    break;
                }
            }
        }

        return lastId;
    }

    /**
     * 获得后一篇文章ID
     *
     * @param ids
     * @param curentId
     * @return
     */
    public static long getNextId(List<Long> ids, long curentId) {
        long nextId = 0;

        if (ids.size() > 0) {
            for (Long id : ids) {
                if (id > curentId) {
                    nextId = id;
                    break;
                }
            }
        }

        return nextId;
    }

    /**
     * 返回字符串
     *
     * @param obj
     * @return
     */
    public static String getStr(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    /**
     *
     *
     *
     */

    public static double getDouble(Object obj) {
        return obj != null && obj != "" ? Double.parseDouble(obj.toString())
                : 0.00;
    }

    /**
     * 返回Integer
     *
     * @param obj
     * @return
     */
    public static Integer getInt(Object obj) {
        return obj != null ? Integer.valueOf(obj.toString()) : 0;
    }

    public static Integer getInteger(Object obj) {
        return obj != null ? Integer.valueOf(obj.toString()) : -99;
    }

    /**
     * 返回Long
     *
     * @param obj
     * @return
     */
    public static Long getLong(Object obj) {
        return obj != null ? Long.valueOf(obj.toString()) : 0;
    }

    /**
     * 返回Long
     *
     * @param obj
     * @return
     */
    public static Long getLongNull(Object obj) {
        return obj != null ? Long.valueOf(obj.toString()) : -99;
    }

    public static String getStartDate(Object obj) {
        if (obj != null) {
            obj = obj + " 00:00:00";
            return obj.toString();
        } else {
            return "";
        }

    }

    public static String endStartDate(Object obj) {
        if (obj != null) {
            obj = obj + " 23:59:59";
            return obj.toString();
        } else {
            return "";
        }

    }

    /**
     * 返回日期的字符串
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getDateStr(Object obj) {
        java.sql.Timestamp dateTime = (java.sql.Timestamp) obj;
        String dateStr = "-";
        if (dateTime != null) {
            dateStr = StringUtils.getStr(dateTime.toLocaleString());
        }

        return dateStr;
    }

    /**
     * 将Long型日期转化成字符串
     *
     * @param obj
     * @return
     */
//    public static String getLong2DateStr(Object obj) {
//        String dateStr = "-";
//        long longDate = getLong(obj);
//        if (longDate > 0) {
//            dateStr = DateUtils.long2LongString(longDate);
//        }
//
//        return dateStr;
//    }

    /**
     * 判断是否为数字类型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 如果是手机号码，则隐藏手机号码
     *
     * @param mobiles
     * @return
     */
    public static String hidePhoneNum(String mobiles) {
        if (isPhone(mobiles)) {
            return mobiles.substring(0, 7) + "****";
        }

        return mobiles;
    }

    /**
     * 判断手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 终止日期的时间
     *
     * @param obj
     * @return
     */
    public static String getEndDate(Object obj) {
        if (obj != null) {
            obj = obj + " 23:59:59";
            return obj.toString();
        } else {
            return "";
        }
    }

    // /**
    // * 返回DateTime
    // *
    // * @param obj
    // * @return
    // */
    // public static DateTime getDateTime(Object obj){
    // return obj != null ? DateUtils.toString(obj.toString(),
    // DateUtils.PATTERN_DATETIME) : null;
    // }

	/*
	 * public static String getFileExt(String fileName) { if
	 * (isNoEmpty(fileName)) { List<String> array = getListWithSpecial(fileName,
	 * POINT); if (array.size() > 0) { return "." + array.get(array.size() - 1);
	 * } } return null; }
	 */
    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }
    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }
    public static boolean isNotEmpty(String str){
        return !StringUtils.isEmpty(str);
    }
    public static String getFilenameExtension(String str){
        int i = str.lastIndexOf(".");
        return str.substring(i+1,str.length());
    }

    public static void main(String[] args) {
        System.out.println(!StringUtils.isPhone("121212"));
    }
}
