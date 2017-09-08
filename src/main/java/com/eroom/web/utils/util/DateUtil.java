package com.eroom.web.utils.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static org.apache.commons.logging.Log log = LogFactory.getLog(DateUtil.class);

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";

	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String HH_MM_SS = "HH:mm:ss";

	public static final String YYYYMM = "yyyyMM";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	/**
	 * 获取系统时间
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 获取系统时间
	 */
	public static Timestamp getSysDate() {
		Timestamp ts = null;
		try {
			ts = new Timestamp(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static Timestamp getTimestamp(Date date){
		return new Timestamp(date.getTime());
	}

	/**
	 * 根据指定的格式输入时间字符串
	 */
	public static String getDateString(String pattern) {
		Timestamp time = getSysDate();
		DateFormat dfmt = new SimpleDateFormat(pattern);
		Date date = time;
		return dfmt.format(date);
	}

	/**
	 * 格式化日期串
	 */
	public static String getDateString(Date date, String formatStr) {
		if (formatStr == null || null == date) {
			return "";
		} else {
			return new SimpleDateFormat(formatStr).format(date);
		}
	}

	/**
	 * 判断时间是否符合格式要求
	 */
	public static boolean isValidDate(String str, String fomat) {
		boolean flag = true;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fomat);
			sdf.parse(str);
			flag = true;
		} catch (ParseException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 按指定格式将字符串转换为日期对象
	 */
	public static Date getDate(String str, String formatString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date date = null;
		date = format.parse(str);
		return date;
	}

	/**
	 * 获取指定时间点偏移天数后的日期
	 */
	public static Timestamp getOffsetDaysDate(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间的偏移天数后的时间
	 */
	public static Timestamp getOffsetDaysTime(Timestamp sysDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间的偏移月份后的时间
	 */
	public static Timestamp getOffsetMonthsTime(Timestamp sysDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.add(Calendar.MONTH, offset);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间的偏移自然月份后的时间
	 */
	public static Timestamp getOffsetNatureMonthsTime(Timestamp sysDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		Timestamp monthStartTime = DateUtil.getTimeThisMonthFirstSec(sysDate);
		calendar.setTime(monthStartTime);
		calendar.add(Calendar.MONTH, offset);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定时间的偏移年份后的时间
	 */
	public static Timestamp getOffsetYearsTime(Timestamp sysDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.add(Calendar.YEAR, offset);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取一天的第一秒 如：2011-11-11 00:00:00
	 */
	public static Timestamp getTheDayFirstSecond(Timestamp sysDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.SECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取一天的最后一秒 如：2011-11-11 23:59:59
	 */
	public static Date getTheDayLastSecond(Date sysDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取本月第一秒
	 */
	public static Timestamp getTimeThisMonthFirstSec(Timestamp sysDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.SECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取本月最后一秒
	 */
	public static Timestamp getTimeThisMonthLastSec(Timestamp sysDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sysDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取当月总的天数
	 */
	public static int getDaysOfThisMonth() {
		Timestamp currTimestamp = DateUtil.getSysDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currTimestamp);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 两个日期相差月份
	 */
	public static int getTimeDifference(String beginDate, String endDate) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMM");

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		try {
			cal1.setTime(timeformat.parse(endDate));
			cal2.setTime(timeformat.parse(beginDate));
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
				- cal2.get(Calendar.MONTH);
		return c;
	}

	/**
	 * 计算两个日期之间相差天数
	 */
	public static int getDaysBetween(String beginDate, String endDate) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		long between_days = 0;
		try {
			cal.setTime(timeformat.parse(beginDate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(timeformat.parse(endDate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期的时间差(秒)
	 */
	public static long getSecondDif(Timestamp formatTime1, Timestamp formatTime2) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		// 毫秒ms
		long diff = t1 - t2;
		long diffSecond = diff / (1000);
		return diffSecond;
	}

	/**
	 * 计算两个日期的时间差(分钟)
	 */
	public static long getMinuteDif(Timestamp formatTime1, Timestamp formatTime2) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		// 毫秒ms
		long diff = t1 - t2;
		long diffMins = diff / (60 * 1000);
		return diffMins;
	}

	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 * 
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}

	/**
	 * 获取系统年月
	 */
	public static String getCurYM() {
		SimpleDateFormat df = new SimpleDateFormat(YYYYMM);// 设置日期格式
		Calendar calender = Calendar.getInstance();
		return df.format(calender.getTime());
	}

	/**
	 * 将指定格式的日期字符串转成Timestamp
	 */
	public static Timestamp getTimestamp(String time, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(time).getTime());
		} catch (ParseException e) {
			log.error(e.toString(), e);
		}
		return ts;
	}

	/**
	 * 获取上月的最后一秒 如：2011-11-11 23:59:59
	 */
	public static Timestamp getTimeLastMonthLastSec(Timestamp sysDate) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(sysDate);
		// 调到上个月
		cal.add(Calendar.MONTH, -1);
		// 得到一个月最最后一天日期(31/30/29/28)
		int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 设置当天的某个时间点
	 */
	public static Timestamp getCurrentTime(int hour, int minute, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSysDate());
		// 设置时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), hour, minute, seconds);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 获取当天是周几(1,2,3,4,5,6,7)
	 */
	public static int getWeekOfDay() {
		Calendar cal = Calendar.getInstance();
		// 一周第一天是否为星期天
		boolean flag = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if (flag) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		return weekDay;
	}

	/**
	 * 获取中文显示的日期
	 * 
	 * @return
	 * @author yicj
	 */
	public static String getCnDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		String date = year + "年" + month + "月" + day + "日  " + hour + "时" + minute + "分";
		return date;
	}
}
