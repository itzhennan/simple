package cn.zznlin.simple.common.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Simon Lv
 * @since 2012-6-10
 */
public class DateUtils {

	public static final String YYMMDD = "yyMMdd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATETIME_M = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_DATETIME_YYYYMMMDD = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_DATETIME_YYYYMMDD = "dd/MM/yyyy";
	public static final String DATETIME_YYYYMMDD = "yyyy/MM/dd";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_MONTH = "yyyy-MM";
	public static final String YEAR = "yyyy";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static DateTime toDateTime(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN_DATETIME).parseDateTime(dateTime);
	}

	// public static void main(String[] args) {
	// String result = yyyymmddSYYmmdd("2016-10-8");
	// System.out.println(result);
	// }

	public static DateTime strToDateTime(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN_DATE).parseDateTime(dateTime);
	}

	public static DateTime str2DateTime(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN_DATETIME).parseDateTime(dateTime);
	}

	public static String toString(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME);
	}

	//DateTime 类型转换成STring 类型不带秒
	public static String toStringNos(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME_M );
	}
	
	public static String dateToString(DateTime dateTime) {
		return dateTime.toString(YEAR);
	}

	public static String dateTimeToString(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATE);
	}

	public static String dateTime2String(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME);
	}

	public static String toString(String dateTime, String pattern) {
		return new DateTime(dateTime).toString(pattern);
	}

	public static String toString(DateTime dateTime, String pattern) {
		return dateTime.toString(pattern);
	}

	public static String toString(LocalDate localDate, String pattern) {
		if (localDate == null) {
			return null;
		}
		return localDate.toString(pattern);
	}

	public static String todayToString() {
		return new LocalDate().toString(YYMMDD);
	}

	public static String todayToStringYear() {
		return new LocalDate().toString(YEAR);
	}

	public static String todayToString2() {
		return new LocalDate().toString(YYYYMMDD);
	}

	public static String todayToYYYYMMDDStr() {
		return new LocalDate().toString(PATTERN_DATE);
	}

	public static String todayToPatternStr(String pattern) {
		return new LocalDate().toString(pattern);
	}

	public static String todayToChinaStr() {
		Calendar calendar = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append("年").append(calendar.get(Calendar.MONTH) + 1).append("月")
				.append(calendar.get(Calendar.DAY_OF_MONTH)).append("日").append(calendar.get(Calendar.HOUR)).append("时")
				.append(calendar.get(Calendar.MINUTE)).append("分");

		return sb.toString();
	}

	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param createTime
	 * @return
	 */
	public static String getNormalDateFromWebChat(String createTime) {
		Long msgCreateTime = Long.parseLong(createTime) * 1000;

		return DATE_FORMAT.format(new Date(msgCreateTime));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String long2LongString(long date) {
		if (date == -99) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(new Date(date));
	}

	public static String long2DateString(Long date, String pattern) {
		if (date == null || date.longValue() == 0) {
			return "";
		}
		if (date.longValue() == -99) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd 日 HH:mm
	 */
	public static String longToDatemString(long date) {
		if (date == -99) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 日 HH:mm");
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm
	 */
	public static String long2DatemString(long date) {
		if (date == -99) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME_M);
		return sdf.format(new Date(date));
	}

	/**
	 * 将long型时间格式转换为字符格式
	 * 
	 * @param date
	 * @return 返回字符串格式 yyyy-MM-dd
	 */
	public static String long2String(Long date) {
		if (null == date || date == -99 || date == 0) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		return sdf.format(new Date(date));
	}
	
	/**
	 * 将Long型时间格式转换为字符格式
	 * 
	 * @param date
	 * @return 返回字符串格式  yyyy-MM-dd HH:mm
	 */
	public static String longToString(Long date) {
		if (null == date || date == -99 || date == 0) {
			return "-";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME_M );
		return sdf.format(new Date(date));
	}
	

	/***
	 * YYYY-MM-DD
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long str2Long(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return 0l;
		}
		Date date = string2Date(dateTime);

		return date != null ? date.getTime() : 0l;
	}
	
	public static long str2Long(String dateTime, String pattern) {
		if (StringUtils.isBlank(dateTime)) {
			return 0l;
		}
		Date date = string2Datetime(dateTime, pattern);
		
		return date != null ? date.getTime() : 0l;
	}

	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long strSLong(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return 0l;
		}
		Date date = string2Datetime(dateTime);
		return date != null ? date.getTime() : 0l;
	}

	/**
	 * 将DateTime型时间格式转换为long
	 * 
	 * @return
	 */
	public static long date2Long(DateTime dateTime) {
		if (dateTime == null) {
			return 0l;
		}
		String date1 = dateTimeToString(dateTime);
		Date date = string2Date(date1);
		return date != null ? date.getTime() : 0l;
	}

	/**
	 * 将DateTime型时间格式转换为long
	 * 
	 * @return
	 */
	public static long date3Long(DateTime dateTime) {
		if (dateTime == null) {
			return 0l;
		}
		String date1 = dateTimeToString(dateTime);
		Date date = string2Date(date1);
		return date != null ? date.getTime() : 0l;
	}

	/**
	 * 获得精确到秒的日期类型 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date string2Datetime(String date) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}
	
	public static Date string2Datetime(String date, String pattern) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 获得日期类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date string2Date(String date) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 日期转换为Long
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long str2MonthLong(String dateTime) {
		if (StringUtils.isEmpty(dateTime)) {
			return 0l;
		}
		Date date = string2MonthDate(dateTime);

		return date != null ? date.getTime() : 0l;
	}

	/**
	 * 获得月份类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date string2MonthDate(String date) {
		Date retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_MONTH);
		try {
			retValue = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/***
	 * YYYY-MM-DD
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(String date) {
		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Calendar calendar = Calendar.getInstance();// 获得一个日历
		calendar.set(year, month - 1, day);// 设置当前时间,月份是从0月开始计算
		int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
		return number;
	}

	/**
	 * 获取指定日期一年前时间
	 * 
	 * @return 返回long格式
	 */
	public static long getChainYear(long date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(date));
		cal.add(Calendar.YEAR, -1);
		return cal.getTime().getTime();
	}

	/***
	 * 时间差，分钟
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String timeDifference(long startTime, long endTime) {
		long timeDifferenceSecond = endTime - startTime;
		double dmin = ((double) timeDifferenceSecond / (60 * 1000));
		BigDecimal bd = BigDecimal.valueOf(dmin);
		DecimalFormat df = new DecimalFormat("0");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(bd);
	}

	public static Date getTimesWeekMoningDate(int h, int m, int s) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int monday = cal.get(Calendar.MONDAY);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, monday, dayOfMonth, h, m, s);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	public static String getPreWeekNight() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 本周开始时间 yyyy-MM-dd HH:mm:ss 0点
	 * 
	 * @return
	 */
	public static String getTimesWeekMorning() {
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(getTimesWeekMoningDate(0, 0, 0));
		return dateString;
	}

	/**
	 * 本周开始时间 yyyy-MM-dd HH:mm:ss 0点
	 * 
	 * @return
	 */
	public static String getDateWeekMorning() {
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(getTimesWeekMoningDate(0, 0, 0));
		return dateString;
	}

	/**
	 * 本周结束时间 yyyy-MM-dd HH:mm:ss 24点
	 * 
	 * @return
	 */
	public static String getTimesWeekNight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekMoningDate(0, 0, 0));
		cal.add(Calendar.DAY_OF_WEEK, 7);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 本周结束时间 yyyy-MM-dd HH:mm:ss 24点
	 * 
	 * @return
	 */
	public static String getDateWeekNight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekMoningDate(0, 0, 0));
		cal.add(Calendar.DAY_OF_WEEK, 7);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * 本月开始时间0点
	 * 
	 * @return
	 */
	public static String getTimesMonthMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	// public static void main(String[] args) {
	// System.out.println(strSLong(getDateMonthMorning(PATTERN_DATETIME)));
	// System.out.println(strSLong(getTimesMonthNight(PATTERN_DATETIME)));
	// }

	/***
	 * 本月开始时间0点
	 * 
	 * @return
	 */
	public static String getDateMonthMorning(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * yyyy-MM时间的 月开始时间0点
	 * 
	 * @return
	 */
	public static String getDateMonthMorning(String date, String pattern) {
		String[] split = date.split("-");

		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * yyyy-MM时间的 月结束时间23:59:59点
	 * 
	 * @return
	 */
	public static String getTimesMonthNight(String date, String pattern) {
		String[] split = date.split("-");
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(split[0]);
		int monday = Integer.parseInt(split[1]) - 1;
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, monday, dayOfMonth, 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

//	public static void main(String[] args) {
//		System.out.println(getDateMonthMorning("2017-06", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(getTimesMonthNight("2017-06", "yyyy-MM-dd HH:mm:ss"));
//	}
	
	//
//	public static Long getLongTimeEndByParam(int param) {
//		Calendar ca = Calendar.getInstance();
//		ca.add(Calendar.MONDAY, param);
//		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY), ca.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
//		return ca.getTime().getTime();
//	}
//	
//	public static void main(String[] args) {
////		int day = 10;
////		System.out.println(new BigDecimal(Math.ceil(day/7.0)+"").intValue());
//		System.out.println(""+long2LongString(1514346997897L));
////		System.out.println(""+long2LongString(1511020800000L));
//	}

	/***
	 * 本月结束时间23:59:59
	 * 
	 * @return
	 */
	public static String getTimesMonthNight(String pattern) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int monday = cal.get(Calendar.MONDAY);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, monday, dayOfMonth, 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * 本月开始时间0点 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateMonthMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * 本月结束时间24点
	 * 
	 * @return
	 */
	public static String getTimesMonthNight() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int monday = cal.get(Calendar.MONDAY);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, monday, dayOfMonth, 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/***
	 * 本月结束时间24点 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateMonthNight() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int monday = cal.get(Calendar.MONDAY);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, monday, dayOfMonth, 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	public static String getPreMonthNight() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}

	// 指定天前后N天开始
	public static Long getLongTimeStartByParam(long dateMillis, int param) {
		DateTime date = new DateTime(dateMillis);
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(dateMillis);
		ca.add(Calendar.DATE, param);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY), ca.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime().getTime();
	}
	
	// 指定天前后N天时间
	public static Long getLongTimeByParam(long dateMillis, int param) {
		DateTime date = new DateTime(dateMillis);
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(dateMillis);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		ca.add(Calendar.DATE, param);
		int i = ca.get(Calendar.DAY_OF_MONTH);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY),i, date.getHourOfDay(), date.getMinuteOfHour(), date.getSecondOfMinute());
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime().getTime();
	}
	
	// 指定天前后N天时间结束
	public static Long getLongTimeEndByParam(long dateMillis, int param) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(dateMillis);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		ca.add(Calendar.DATE, param);
		int i = ca.get(Calendar.DAY_OF_MONTH);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY),i, 23, 59, 59);
		ca.set(Calendar.MILLISECOND, 999);
		return ca.getTime().getTime();
	}
	
	
	
//	public static void main(String[] args) {
//		System.out.println(long2LongString(getLongTimeStartByParam(System.currentTimeMillis(),1)));
//		long twoMillis = 2*24*60*60*1000;
//		BigDecimal twoBigDecimal = new BigDecimal(String.valueOf(twoMillis));
//		BigDecimal startByParam = new BigDecimal(String.valueOf(System.currentTimeMillis()));
//		BigDecimal sumTime = startByParam.add(twoBigDecimal);
//		System.out.println(sumTime.longValue());
//		System.out.println(System.currentTimeMillis()+3*24*60*60*1000L);
//		long currentTimeMillis = System.currentTimeMillis();
//		System.out.println(long2LongString(currentTimeMillis));
//		System.out.println(long2LongString(getLongTimeByParam(currentTimeMillis, 1)));
//	}
	
	// 当前天前后N天开始
	public static Long getLongTimeStartByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, param);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY), ca.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime().getTime();
	}
	
	// 当前天前后N天结束
	public static Long getLongTimeEndByParam(int param) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, param);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY), ca.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		ca.set(Calendar.MILLISECOND, 999);
		return ca.getTime().getTime();
	}
	
	/**
	 * 获得昨天开始的时间
	 * 
	 * @return
	 */
	public static String getDateYesterdayStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 昨天的结束时间
	 * 
	 * @return
	 */
	public static String getDateYesterdayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}
	
	/**
	 * 获得前后N天开始的时间
	 * 
	 * @return
	 */
	public static String getDateYesterdayStart(int day,String patten) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}
	
	/**
	 * 前后N天的结束时间
	 * 
	 * @return
	 */
	public static String getDateYesterdayEnd(int day,String patten) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, day);
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 获得今天开始的时间
	 * 
	 * @return
	 */
	public static String getDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DATE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 今天的结束时间
	 * 
	 * @return
	 */
	public static String getDateEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.add(Calendar.DATE, 0);
		cal.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	// 获取一天的开始时间
	public static String getStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	// 获取一天的结束时间
	public static String getEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATE);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 时间前推或后推分钟,其中minute表示分钟.
	 * 
	 * @param date
	 *            必须是yyyy-MM-dd HH:mm:ss格式
	 * @param minute
	 *            正数表示后退，负数表示前退
	 * 
	 */
	public static String getPreTime(String date, String minute) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(date);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(minute) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 将yyyy/MM/dd HH:mm:ss 转成 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 * @return
	 */
	public static String yyyymmdd2YYmmdd(String dateString) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		DateTime retValue = null;
		try {
			retValue = DateTimeFormat.forPattern(PATTERN_DATETIME_YYYYMMMDD).parseDateTime(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toString(retValue);
	}

	/**
	 * 将yyyy/MM/dd 转成 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 * @return
	 */
	public static String yyyymmddSYYmmdd(String dateString) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		DateTime retValue = null;
		try {
			retValue = DateTimeFormat.forPattern(DATETIME_YYYYMMDD).parseDateTime(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toString(retValue);
	}

	/**
	 * 判断当前系统时间是否在给定的二个时间之间
	 * 
	 * @param dateStr1
	 *            【格式是：yyyy-MM-dd HH:mm:ss】
	 * @param dateStr2
	 * @return
	 */
	public static boolean getIsBetween(String dateStr1, String dateStr2) {
		if (StringUtils.isNoEmpty(dateStr1)
				&& StringUtils.isNoEmpty(dateStr2)) {
			Date date1 = string2Datetime(dateStr1);
			Date date2 = string2Datetime(dateStr2);
			Date nowDate = new Date();
			if (nowDate.after(date1) && nowDate.before(date2)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public static String dateTimetoStringDATETIME(DateTime dateTime) {
		return dateTime.toString(PATTERN_DATETIME);
	}

	// Date类型转换为字符串 && 将字符串转换为Date类型
	public static String datetime2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME);
		return sdf.format(date);
	}

	// ss convert hh:mm:ss
	public static String covertTime(Long second) {

		StringBuilder sb = new StringBuilder();
		Double hour = Math.pow(60, 2);
		Long hh = second / hour.longValue();
		Long mm = second % hour.longValue() / 60;
		Long ss = second % hour.longValue() % 60;
		sb.append(builder(hh)).append(":").append(builder(mm)).append(":").append(builder(ss));

		return sb.toString();

	}

	public static String currentDate(String patten) {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(new Date(currentTime));
	}

	// 当前天前后N天开始
	public static Long getLongTimeStartByParam(int param, long millis) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(millis);
		ca.add(Calendar.DATE, param);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONDAY), ca.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return ca.getTime().getTime();
	}

	/**
	 * 获取具体月差的字符串 举例：0 本月 1 下个月 -1 上个月
	 * 
	 * @return
	 */
	public static String getYMStr(int anount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, anount);
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_MONTH);
		return sdf.format(cal.getTime());
	}

	public static Object builder(Long obj) {
		if (obj / 10 > 0) {
			return obj;
		} else {
			return "0" + obj;
		}

	}

	public static String getWeekOfMonth(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		String strWeek = "";
		int dw = calendar.get(Calendar.DAY_OF_WEEK);
		if (dw == 1) {
			strWeek = "星期天";
		} else if (dw == 2) {
			strWeek = "星期一";
		} else if (dw == 3) {
			strWeek = "星期二";
		} else if (dw == 4) {
			strWeek = "星期三";
		} else if (dw == 5) {
			strWeek = "星期四";
		} else if (dw == 6) {
			strWeek = "星期五";
		} else if (dw == 7) {
			strWeek = "星期六";
		}
		return strWeek;
	}
	
	
//	public static void main(String[] args){  
//		String currentTimeMillis = DateUtils.long2LongString(System.currentTimeMillis());
//		String nextVisitTime = DateUtils.long2LongString(1513699200000L);
//        System.out.println(DateUtils.daysBetween(currentTimeMillis,nextVisitTime));  
//        System.out.println(DateUtils.daysBetween2(currentTimeMillis,nextVisitTime));  
//    }
      
    /** 
     * http://blog.csdn.net/wpydaguan/article/details/46235349  java计算两个日期之间相差天数和相隔天数详解
     * 计算两个日期之间相隔的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     */ 
    public static int daysBetween(String smdate,String bdate){  
        try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = Math.abs((time2 - time1)) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			
		}     
        return -99;
    }
    
    
    /** 
     * http://blog.csdn.net/wpydaguan/article/details/46235349  java计算两个日期之间相差天数和相隔天数详解
     * 计算两个日期之间相隔的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @param time 再减去多少个小时
     * @return 相差天数 
     */ 
    public static int daysBetween(String smdate,String bdate,Long time){  
        try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = Math.abs((time2 - time1 -time)) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			
		}     
        return -99;
    }
    
    
    public static void main(String[] args) {
    	System.out.println(daysBetween("2018-03-09 15:32:00", "2018-02-28 18:00:00"));
//    	System.out.println(daysBetween("2018-02-29 16:00:00", "2018-02-28 18:00:00"));
//    	System.out.println(daysBetween2("2018-03-09 15:32:00", "2018-02-28 18:00:00"));
	}
    
    /**  
     * http://blog.csdn.net/wpydaguan/article/details/46235349  java计算两个日期之间相差天数和相隔天数详解
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     */
    public static int daysBetween2(String smdate,String bdate){  
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(sdf.parse(smdate));
    		long time1 = cal.getTimeInMillis();
    		cal.setTime(sdf.parse(bdate));
    		long time2 = cal.getTimeInMillis();
    		long between_days = Math.abs((time2 - time1)) / (1000 * 3600 * 24);
    		return Integer.parseInt(String.valueOf(between_days));
    	} catch (Exception e) {
    		
    	}     
    	return -99;
    } 
    
    public static double hourBetween2(String smdate,String bdate){  
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(sdf.parse(smdate));
    		long time1 = cal.getTimeInMillis();
    		cal.setTime(sdf.parse(bdate));
    		long time2 = cal.getTimeInMillis();
    		double between_hours = Math.abs((time2 - time1)) * 1.0 / (1000 * 60 * 60);
    		return new BigDecimal(String.valueOf(between_hours)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    	} catch (Exception e) {
    		
    	}     
    	return -99;
    }  
    
//    public static void main(String[] args) {
//    	String smdate = "2018-02-21 12:00:00";
//    	String bdate = "2018-02-21 14:30:00";
//    	System.out.println(hourBetween2(smdate, bdate));
//	}
    
    public static int daysBetweenMinute(String smdate,String bdate){  
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(sdf.parse(smdate));
    		long time1 = cal.getTimeInMillis();
    		cal.setTime(sdf.parse(bdate));
    		long time2 = cal.getTimeInMillis();
    		long between_days = Math.abs((time2 - time1)) / (1000 * 60);
    		return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			
		}     
        return -99;
    }
    
}
