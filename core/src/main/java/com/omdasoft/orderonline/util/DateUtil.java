/**
 * 
 */
package com.omdasoft.orderonline.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Abstraction of DateTimeProvider. User need to implement the
 * {@link #getTime()} method.
 * 
 * @author cyril
 * @since 0.2.0
 */
public class DateUtil {
	private static Random random = new Random();

	public static Date getTime() {
		return new Date();
	}

	/**
	 * 加上N天后是哪一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addSomeDay(Date date, long day) {
		long s1 = date.getTime();
		long s = day * (1000 * 60 * 60 * 24);
		long s2 = s + s1;
		return new Date(s2);
	}

	public static Date getLastTimeOfThisDay(Date date) {
		Date para = null;
		para = date == null ? new Date() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(para);
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	public static Date getEarlierTimeOfThisDay(Date date) {
		Date para = null;
		para = date == null ? new Date() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(para);
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 取得参数时间后的parameter天的最早时间
	 * 
	 * like : Fri Sep 04 00:00:00 CST 2009
	 * 
	 * @param date
	 * @param parameter
	 * @return
	 */
	public static Date getDateOfParameterOnDay(Date date, int parameter) {
		Date para = null;
		para = date == null ? new Date() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(para);
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + parameter);
		return calendar.getTime();
	}

	public static Date getDateOfParameterOnYear(Date date, int parameter) {
		Date para = null;
		para = date == null ? new Date() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(para);
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + parameter);
		return calendar.getTime();
	}

	public static Date getDateOfParameterOnMonth(Date date, int parameter) {
		Date para = null;
		para = date == null ? new Date() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(para);
		// calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) + parameter);
		return calendar.getTime();
	}

	public static String formatData(String partten, Date date) {
		if (StringUtil.isEmptyString(partten)) {
			partten = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		return sdf.format(date);
	}

	public static int betweenDays(Date begin, Date end) {
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(begin);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(end);
		if (beginDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)) {
			return endDate.get(Calendar.DAY_OF_YEAR)
					- beginDate.get(Calendar.DAY_OF_YEAR);
		} else {
			if (beginDate.getTimeInMillis() < endDate.getTimeInMillis()) {
				int days = beginDate.getActualMaximum(Calendar.DAY_OF_YEAR)
						- beginDate.get(Calendar.DAY_OF_YEAR)
						+ endDate.get(Calendar.DAY_OF_YEAR);
				for (int i = beginDate.get(Calendar.YEAR) + 1; i < endDate
						.get(Calendar.YEAR); i++) {
					Calendar c = Calendar.getInstance();
					c.set(Calendar.YEAR, i);
					days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
				}
				return days;
			} else {
				int days = endDate.getActualMaximum(Calendar.DAY_OF_YEAR)
						- endDate.get(Calendar.DAY_OF_YEAR)
						+ beginDate.get(Calendar.DAY_OF_YEAR);
				for (int i = endDate.get(Calendar.YEAR) + 1; i < beginDate
						.get(Calendar.YEAR); i++) {
					Calendar c = Calendar.getInstance();
					c.set(Calendar.YEAR, i);
					days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
				}
				return days;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean compareData(Date date1, Date date2) {
		if (date1.getYear() == date2.getYear()
				&& date1.getMonth() == date2.getMonth()
				&& date1.getDate() == date2.getDate()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 当前日期字符串
	 * */
	public static String getDateString(String pattern) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(calendar.getTime());
		// return "111111225";
	}

	/**
	 * 得到随机�?
	 * 
	 * @param max
	 *            �?大随机数
	 * @return
	 */

	public static int rand(int max) {
		return random.nextInt(max + 1);
	}
	public static Date getStringDatess(String sdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		sdf.setLenient(false);

		Date ddate = null;
		try {
			ddate = sdf.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddate;
	}
	public static Date getStringDate(String sdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		sdf.setLenient(false);

		Date ddate = null;

		ddate = sdf.parse(sdate);

		return ddate;
	}
	
	/**
	 * 去掉时间,留下年月日
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToString(Date d) {
		if (d == null) {
			return null;
		}
		return (1900 + d.getYear()) + "-" + (d.getMonth() + 1) + "-"
				+ d.getDate();
	}
	/**
	 * 去掉时间,留下时
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToStringH(Date d) {
		if (d == null) {
			return null;
		}
		return d.getHours()+"";
	}
	/**
	 * 去掉时间,留下分
	 * 
	 * @param d
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dateToStringS(Date d) {
		if (d == null) {
			return null;
		}
		return d.getMinutes()+"";
	}
}
