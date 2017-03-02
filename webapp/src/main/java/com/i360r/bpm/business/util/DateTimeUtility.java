package com.i360r.bpm.business.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class DateTimeUtility {
    
    private static ThreadLocal<Map<DateFormatType, DateFormat>> dateFormats = new ThreadLocal<Map<DateFormatType, DateFormat>>();   
    
    public static final long MINUTE = TimeUnit.MINUTES.toMillis(1);
    
    static enum DateFormatType {
        
    	YYYYMMDDHHMMSS_NOSPACE("yyyyMMddHHmmss"),	// 20091227091010
        YYYYMMDDHHMMSS("yyyy-MM-dd HH:mm:ss"),
        YYYYMMDDHHMM("yyyy-MM-dd HH:mm"),
        YYYYMMDD("yyyy-MM-dd"),
        YYYYMM("yyyy年MM月"),
        MMDDHHMM("MM-dd HH:mm"),
        HHMMSS("HH:mm:ss"),
        HHMM("HH:mm");

        private String pattern;
        
        DateFormatType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }               
    }
    
    public static final long ONE_MINUTE = TimeUnit.MINUTES.toMillis(1);
    
    private static DateFormat getDateFormat(DateFormatType dateFormatType) {
        Map<DateFormatType, DateFormat> dateFormatMap = dateFormats.get();
        
        if (dateFormatMap == null) {
            dateFormatMap = new HashMap<DateFormatType, DateFormat>();
            dateFormats.set(dateFormatMap);
        }
        
        DateFormat dateFormat = dateFormatMap.get(dateFormatType);
        
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(dateFormatType.getPattern());
            dateFormatMap.put(dateFormatType, dateFormat);
        }
        
        return dateFormat;
    }
    
    // 将时间按配置的格式转换为String
    public static String formatNoSpaceYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMMSS_NOSPACE).format(date);
    }
    
    
    public static String formatYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMMSS).format(date);
    }
    
    public static String formatYYYYMM(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.YYYYMM).format(date);
    }
    
    public static String formatYYYYMMDDHHMMSS(Calendar cal) {
        if (cal == null) {
            return null;
        }

        return formatYYYYMMDDHHMMSS(cal.getTime());
    }
    
    public static String formatYYYYMMDDHHMM(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMM).format(date);
    }
    
    public static String formatYYYYMMDD(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.YYYYMMDD).format(date);
    }
  
    public static String formatYYYYMMDD(Calendar cal) {
    	if (cal == null) {
            return "";
        }

        return formatYYYYMMDD(cal.getTime());
    }
    
    public static String formatMMDDHHMM(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.MMDDHHMM).format(date);
    }
    
    public static String formatHHMM(Date date) {
        if (date == null) {
            return "";
        }
        
        return getDateFormat(DateFormatType.HHMM).format(date);
    }
    
    public static String formatHHMM(Calendar cal) {
        if (cal == null) {
            return null;
        }

        return formatHHMM(cal.getTime());
    }

    /**
	 * 将描述转成 00:00:00字符串
	 * 
	 * @param second 秒
	 * @return
	 */
	public static String formatHHMMSS(long millisecond) {
		
		int second = (int) (millisecond / 1000);
		
		int hour = second / (60 * 60);
		// 剩余的分
		int minuteLeft = second - hour * 60 * 60;
		
		int minute = minuteLeft / 60;
		
		int secondTemp = minuteLeft % 60;
		
		String hourStr = String.valueOf(hour);
		if (hourStr.length() == 1) {
			hourStr = "0" + hourStr;
		}
		
		String minuteStr = String.valueOf(minute);
		if (minuteStr.length() == 1) {
			minuteStr = "0" + minuteStr;
		}
		
		String secondStr = String.valueOf(secondTemp);
		if (secondStr.length() == 1) {
			secondStr = "0" + secondStr;
		}
		
		return hourStr + ":" + minuteStr + ":" + secondStr;
	}
    
    // 将时间String转换为Date对象
    public static Date parseNoSpaceYYYYMMDDHHMMSS(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMMSS_NOSPACE).parse(date);
    }
    
    public static Date parseYYYYMMDDHHMMSS(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMMSS).parse(date);
    }
    
    public static Date parseYYYYMMDDHHMM(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.YYYYMMDDHHMM).parse(date);
    }
        
    public static Date parseYYYYMMDD(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.YYYYMMDD).parse(date);
    }
    
    public static Date parseHHMM(String date) throws ParseException {
    	if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.HHMM).parse(date);
    }
    
    public static Date parseHHMMSS(String date) throws ParseException {
    	if (date == null) {
            return null;
        }
        
        return getDateFormat(DateFormatType.HHMMSS).parse(date);
    }
    
    /**
     * @param time - 传入一个时间
     * @return - 将传入时间的日期改为今天后返回
     */
    public static Calendar getTodayTime(Date time) {
    	Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		
		Calendar today = Calendar.getInstance();
		
		today.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
		today.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		
		return today;
    }
    
    /**
     * @param time - 传入一个时间
     * @return - 将传入时间的日期改为今天后返回
     */
    public static Calendar getTodayTime(Calendar time) {        
        Calendar today = Calendar.getInstance();
        
        today.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        today.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        
        return today;
    }
    
    /**
     * 获取参数time加上days天之后的日期
     * 参数days可以为负数
     * @param time 
     * @param days 正数为给参数time加days,负数为给参数time减days
     * @return
     */
    public static Date getDayTime(Date time, int days) {
    	Calendar day = Calendar.getInstance();
    	day.setTime(time);
		
    	day.add(Calendar.DAY_OF_YEAR, days);
		
		return day.getTime();
    }
    
    /**
     * 返回明天的日期
     * @return
     */
    public static Date getTomorrowDate() {
    	return getDayTime(new Date(), 1);
    }
    
    /**
     * 获取所在时间的下一个月
     */
    public static Date getNextMonth(Date now) {
    	return getNextMonth(now, 1);
    }
    
    public static Date getNextMonth(Date now, int n) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(now);
    	c.add(Calendar.MONTH, n);
    	return c.getTime();
    }
    
    /**
     * @param date 传入一个时间
     * @param days 在date时间基础上进行增加or减少的天数
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        
        return cal.getTime();
    }
    
    /**
     * @param time - 传入一个时间
     * @return - 将传入时间的日期改为明天后返回
     */
    public static Calendar getTomorrowTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        Calendar tomorrow = Calendar.getInstance();
        
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
        tomorrow.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
        
        return tomorrow;
    }
    
    /**
     * @param date - 传入一个时间
     * @return - 返回将传入时间日期加一天的时间
     */
    public static Calendar getNextDayTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return cal;
    }
    
    public static Date minusMinutes(Date date, int minutes) {
    	return minusMinutes(date, minutes, null);
    }
    
    /**
     * @Description: 计算提前一定分钟数的时间
     * @param date	基础时间
     * @param minutes	提前分钟数
     * @param notEarlierThan	结果不早于该时间，如果为null则不限制
     * @return
     */
    public static Date minusMinutes(Date date, int minutes, Date notEarlierThan) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MINUTE, -minutes);
    	
    	if (notEarlierThan != null && cal.getTime().before(notEarlierThan)) {
    		return notEarlierThan;
    	}
    	
    	return cal.getTime();
    }
    
    /**
     * @param date - 传入一个时间
     * @param minutes - 传入分钟数，可以为负数
     * @return - 返回 date + minutes的新时间
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        
        return cal.getTime();
    }
    
    /**
     * 在传入时间的基础上添加秒
     * 
     * @param date 时间
     * @param seconds 秒
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.SECOND, seconds);
    	
    	return cal.getTime();
    }
    
    /**
	 * 将cal增加increasement分钟，但是不超过cal所代表的日期
	 * @param cal
	 * @param increasement
	 */
	public static void addMinutesWithinTheDay(Calendar cal, int increasement) {
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        
        if ((hour  * 60 + minute + increasement) >= (24 * 60)) {
            // Set to the max time of the day
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
        } else {
            cal.add(Calendar.MINUTE, increasement);
        }
    }
	
	private static Date getMinTimeOfHour(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		
		return calendar.getTime();
	}
	
	/**
	 * @param date - 传入一个时间
	 * @return - 返回这个时间所在日期的最小时间
	 */
	public static Date getMinTimeOfDay(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getMinTimeOfHour(date));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		
		return calendar.getTime();
	}
	
	private static Date getMaxTimeOfHour(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        
        return calendar.getTime();
    }
    
	/**
	 * @param date - 传入一个时间
	 * @return - 返回这个时间所在日期的最大时间
	 */
    public static Date getMaxTimeOfDay(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.setTime(getMaxTimeOfHour(calendar.getTime()));
        
        return calendar.getTime();
    }

    /**
	 * @return - 返回昨天的最小时间
	 */
	public static Date getMinTimeOfYesterday() {
		Date date = new Date();
		
		date = DateUtils.addDays(date, -1);	
		
		return getMinTimeOfDay(date);
	}
	
	/**
     * @return - 返回今天是一周的第几天，周一是第1天，周日是第7天
     */
    public static int getDayOfWeek() {
    	Calendar cal = Calendar.getInstance();
    	
    	int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
    	
    	if (day == 0) {
    		day = 7;
    	}
    	
    	return day;
    }
    
    /**
     * @return - 返回昨天是一周的第几天，周一是第1天，周日是第7天
     */
    public static int getYesterdayOfWeek() {
        Calendar cal = Calendar.getInstance();
        
        // Get yesterday
        cal.add(Calendar.DAY_OF_WEEK, -1);
        
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        if (day == 0) {
            day = 7;
        }
        
        return day;
    }
    
    /**
     * @return - 返明天是一周的第几天，周一是第1天，周日是第7天
     */
    public static int getTomorrowOfWeek() {
        Calendar cal = Calendar.getInstance();
        
        // Get Tomorrow
        cal.add(Calendar.DAY_OF_WEEK, 1);
        
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        if (day == 0) {
            day = 7;
        }
        
        return day;
    }


    /**
     * 判断今天是否是周末
     * @return
     */
    public static boolean isWeekendDay() {
    	
    	Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 判断今天是否是周六
     * @return
     */
    public static boolean isSaturday() {
    	
    	Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if (day == Calendar.SATURDAY) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 判断今天是否是周日
     * @return
     */
    public static boolean isSunday() {
    	
    	Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if (day == Calendar.SUNDAY) {
            return true;
        }
        
        return false;
    }

    /**
     * 判断今天是否是周五
     * @return
     */
    public static boolean isFriday() {
    	
    	Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if (day == Calendar.FRIDAY) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 判断今天是否是周一
     * @return
     */
    public static boolean isMonday() {
    	Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.MONDAY;
    }
    
    /**
     * 判断两个Date是否同月
     * @return
     */
    public static boolean isTheSameMonth(Date month1, Date month2) {
    	
    	if(month1 == null || month2 == null) {
    		return false;
    	}
    	
    	String monthStr1 = formatYYYYMM(month1);
    	String monthStr2 = formatYYYYMM(month2);
        return monthStr1.equals(monthStr2);
    }
    
    /**
     * @param calendar
     * @return - 返回calendar所在周的周一
     */
    public static Date getMondayOfWeek(Calendar calendar) {
		Calendar calendarNew = Calendar.getInstance();
		calendarNew.setTime(calendar.getTime());
		
		int curDay = calendarNew.get(Calendar.DAY_OF_WEEK);				
		
		if (curDay == Calendar.SUNDAY) {
			calendarNew.add(Calendar.DATE, -6);
		} else {
			calendarNew.add(Calendar.DATE, 2 - curDay);
		}
		
		return calendarNew.getTime();
	}
    
    
	/**
	 *  返回下周一的日期
	 * @return
	 */
	public static Date getMondayOfNextWeek() {
		
		Calendar calendar = Calendar.getInstance();
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);				
		
		if (dayOfWeek == Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, 1);
		} else {
			calendar.add(Calendar.DATE, 9 - dayOfWeek);
		}
		
		return calendar.getTime();
		
	}

	
	/**
	 * @param date 需要设置的时间
	 * @return 将传入时间的日期改为下周一后返回
	 */
	public static Date getMondayOfNextWeek(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Calendar mondayOfNextWeek = Calendar.getInstance();
		mondayOfNextWeek.setTime(getMondayOfNextWeek());
		 
		mondayOfNextWeek.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		mondayOfNextWeek.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		
		return mondayOfNextWeek.getTime();
	}
	
    /**
     * @param calendar
     * @return - 返回calendar所在周的周日
     */
	public static Date getSundayOfWeek(Calendar calendar) {
		Calendar calendarNew = Calendar.getInstance();
		calendarNew.setTime(calendar.getTime());
		
		int curDay = calendarNew.get(Calendar.DAY_OF_WEEK);
		
		if (curDay != Calendar.SUNDAY) {
			calendarNew.add(Calendar.DATE, 8 - curDay);
		} 		
		
		return calendarNew.getTime();
	}

	/**
     * @param calendar
     * @return - 返回calendar所在月的第一天
     */
	public static Calendar getFirstDayOfMonth(Calendar calendar) {
		Calendar calendarNew = Calendar.getInstance();
		calendarNew.setTime(calendar.getTime());
		calendarNew.set(Calendar.DAY_OF_MONTH, calendarNew.getActualMinimum(Calendar.DAY_OF_MONTH));     
		return calendarNew;
	}

	/**
     * @param calendar
     * @return - 返回calendar所在月的最后一天
     */
	public static Calendar getLastDayOfMonth(Calendar calendar) {
		Calendar calendarNew = Calendar.getInstance();
		calendarNew.setTime(calendar.getTime());
		calendarNew.set(Calendar.DAY_OF_MONTH, calendarNew.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		return calendarNew;
	}
    /**
     * 返回某天距离当月月末的天数
     */
	public static int getDaysToMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long time1 = calendar.getTimeInMillis();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		long time2 = calendar.getTimeInMillis();
		int betweenDays = (int) ((time2 - time1)/(1000 * 3600 * 24));
		return betweenDays;
	}
	
	 /**
     * 返回某天距离当月月初的天数
     */
	public static int getDaysToMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long time1 = calendar.getTimeInMillis();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		long time2 = calendar.getTimeInMillis();
		int betweenDays = (int) ((time1 - time2)/(1000 * 3600 * 24));
		return betweenDays;
	}
	
	/**
	 * @param availableDays - "YYYYNNN"，分别表示周一到周日
	 * @return - 如果今日为'Y'就返回true，否则返回false
	 */
    public static boolean isTodayAvailableDay(String availableDays) {
    	int day = getDayOfWeek();
    	String available = availableDays.substring(day - 1, day);
    	if ("N".equals(available.toUpperCase())) {
    		return false;
    	}

        return true;
    }
	
    
	/**
	 * @param availableDays - "YYYYNNN"，分别表示周一到周日
	 * @return - 如果今日为'Y'就返回true，否则返回false
	 */
    public static boolean isTomorrowAvailableDay(String availableDays) {
    	int tomorrow = getTomorrowOfWeek();
    	String available = availableDays.substring(tomorrow - 1, tomorrow);
    	if ("N".equals(available.toUpperCase())) {
    		return false;
    	}

        return true;
    }
    
    
    /**
     * @param first
     * @param second
     * @return - 只比较小时和分钟，不比较其他字段
     */
	public static boolean hourMinuteBefore(Calendar first, Calendar second) {
		
		int firstValue = first.get(Calendar.HOUR_OF_DAY) * 100 + first.get(Calendar.MINUTE);
		int secondValue = second.get(Calendar.HOUR_OF_DAY) * 100 + second.get(Calendar.MINUTE);
		
		if (firstValue < secondValue) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * @param first
     * @param second
     * @return - 只比较小时和分钟，不比较其他字段
     */
	public static boolean hourMinuteBefore(Date first, Date second) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(first);
        
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(second);
	    
        int firstValue = firstCalendar.get(Calendar.HOUR_OF_DAY) * 100 + firstCalendar.get(Calendar.MINUTE);
        int secondValue = secondCalendar.get(Calendar.HOUR_OF_DAY) * 100 + secondCalendar.get(Calendar.MINUTE);
        
        if (firstValue < secondValue) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
     * @param first
     * @param second
     * @return - 只比较小时和分钟，不比较其他字段
     */
	public static boolean hourMinuteAfter(Calendar first, Calendar second) {
		return !hourMinuteBefore(first, second);
	}
	
	/**
     * @param first
     * @param second
     * @return - 只比较小时和分钟，不比较其他字段
     */
	public static boolean hourMinuteAfter(Date first, Date second) {
		return !hourMinuteBefore(first, second);
	}
	
	/**
     * @param first
     * @param second
     * @return - 只比较小时和分钟，不比较其他字段
     */
	private static boolean hourMinuteEqual(Calendar first, Calendar second) {
		
		int firstValue = first.get(Calendar.HOUR_OF_DAY) * 100 + first.get(Calendar.MINUTE);
		int secondValue = second.get(Calendar.HOUR_OF_DAY) * 100 + second.get(Calendar.MINUTE);
		
		if (firstValue == secondValue) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param time
	 * @param from
	 * @param to
	 * @return - 只比较小时和分钟，不比较其他字段
	 */
	public static boolean hourMinuteBetween(String time, Calendar from, Calendar to) {
		Calendar cal = getTodayFromHHMM(time);
		if (cal == null) {
			return false;
		}
		
		return ((hourMinuteBefore(from, cal) && hourMinuteAfter(to, cal)) || hourMinuteEqual(cal, from) || hourMinuteEqual(cal, to));
	}

	/**
	 * @param time
	 * @param from
	 * @param to
	 * @return - 只比较小时和分钟，不比较其他字段
	 */
	public static boolean hourMinuteBetween(Calendar date, Calendar from, Calendar to) {
		return ((hourMinuteBefore(from, date) && hourMinuteAfter(to, date)) || hourMinuteEqual(date, from) || hourMinuteEqual(date, to));
	}
	
	/**
	 * @param time
	 * @param from
	 * @param to
	 * @return - 只比较小时和分钟，不比较其他字段
	 */
	public static boolean hourMinuteBetween(Date date, Date from, Date to) {
	    Calendar dateCalendar = Calendar.getInstance();
	    dateCalendar.setTime(date);
	    
	    Calendar fromCalendar = Calendar.getInstance();
	    fromCalendar.setTime(from);
	    
	    Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(to);
	    
        return ((hourMinuteBefore(fromCalendar, dateCalendar) && hourMinuteAfter(toCalendar, dateCalendar))
        		|| hourMinuteEqual(dateCalendar, fromCalendar) || hourMinuteEqual(dateCalendar, toCalendar));
    }
	
	/**
	 * @param time
	 * @return - 将"HH:MM"转换为今日的时间
	 */
	public static Calendar getTodayFromHHMM(String time) {
		if (StringUtils.isBlank(time) || !time.matches("^(([01][0-9])|(2[0-3]))\\:([0-5][0-9])$")) {
			return null;
		}
		
		String[] subs = time.split(":");
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(subs[0]));
		cal.set(Calendar.MINUTE, Integer.valueOf(subs[1]));
		
		return cal;
	}
	
	/**
	 * @param time
	 * @return - 将"HH:MM"转换为今日的时间
	 */
	public static Calendar getTodayMinFromHHMM(String time) {

		Calendar cal = getTodayFromHHMM(time);
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		
		return cal;
	}
	
	/**
	 * @param hour
	 * @param minute
	 * @return - 将小时和分钟转换为"HH:MM"格式的字符串
	 */
	public static String getHHMMFromHourMinute(int hour, int minute) {
		String hourStr = String.valueOf(hour);
		if (hourStr.length() == 1) {
			hourStr = "0" + hourStr;
		}
		
		String minuteStr = String.valueOf(minute);
		if (minuteStr.length() == 1) {
			minuteStr = "0" + minuteStr;
		}

		return hourStr + ":" + minuteStr;
	}
	
	/**
	 * @param time
	 * @return - 判断time的年月日是不是今天
	 */
	public static boolean isTodayTime(Date time) {
	    Date now = new Date();
	    
	    return DateUtils.isSameDay(time, now);
	}
	
	/**
	 * @param from
	 * @param to
	 * @return - 判断今天的日期（不包括时，分，秒）是不是在from和to之间
	 */
	public static boolean isTodayBetweenRange(Date from, Date to) {
		// 获取今天的日期，不包括含时，分，秒
		Date today = DateUtils.round(new Date(), Calendar.DAY_OF_MONTH);
		Date fromDay = DateUtils.round(from, Calendar.DAY_OF_MONTH);
		Date toDay = DateUtils.round(to, Calendar.DAY_OF_MONTH);
		
		if (fromDay.getTime() <= today.getTime() && today.getTime() <= toDay.getTime()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
	public static int daysBetween(Date smdate, Date bdate) {

		try {

			DateFormat sdf = getDateFormat(DateFormatType.YYYYMMDD);
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));

			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));

		} catch (ParseException e) {

		}

		return -1;
		
	} 
	
	public static int getDailyVersion() {
		return getDailyVersion(new Date());
	}
	
	public static int getDailyVersion(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 10000 
				+ calendar.get(Calendar.MONTH) * 100 
				+ calendar.get(Calendar.DAY_OF_MONTH); 
	}
	
	public static String getDate(String dateTime) {
		try {
			return dateTime == null ? null : formatYYYYMMDD(parseYYYYMMDD(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date getDate(Date dateTime) {
		try {
			return dateTime == null ? null : parseYYYYMMDD(formatYYYYMMDD(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	/**
	 * @Description: 把一个时间段转为这种格式：*天*小时*分*秒
	 * @param seconds
	 * @return
	 */
	public static String getIntervalDesc(long seconds) {
		StringBuilder sb = new StringBuilder();
		long days = seconds/TimeUnit.DAYS.toSeconds(1);
		if (days > 0) {
			seconds = (int)(seconds%TimeUnit.DAYS.toSeconds(1));
			sb.append(days);
			sb.append("天");			
		}
		long hours = seconds/TimeUnit.HOURS.toSeconds(1);
		if (hours > 0) {
			seconds = (int)(seconds%TimeUnit.HOURS.toSeconds(1));
			sb.append(hours);
			sb.append("小时");			
		}
		long minutes = seconds/TimeUnit.MINUTES.toSeconds(1);
		if (minutes > 0) {
			seconds = (int)(seconds%TimeUnit.MINUTES.toSeconds(1));
			sb.append(minutes);
			sb.append("分");			
		}
		if (seconds > 0) {
			sb.append(seconds);
			sb.append("秒");			
		}
		
		return sb.toString();
	}
	
	/**
	 * 计算from到to的秒数，如果from在to之后，返回0
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getIntervalSeconds(Date from, Date to) {
		int seconds = (int)((to.getTime() - from.getTime())/1000);
		return seconds < 0 ? 0 : seconds;
	}
	
	public static int getIntervalMinutes(Date from, Date to) {
		return getIntervalSeconds(from, to) / 60;
	}
	
	public static int getIntervalHours(Date from, Date to) {
		return getIntervalMinutes(from, to) / 60;
	}
	
	public static BigDecimal getIntervalDays(Date from, Date to) {
		int hours = getIntervalHours(from, to);
		double num = Math.ceil(hours / 24.0 * 2)/2;
		return new BigDecimal(num);
	}
	
	public static Date getFirstDayOfMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static void main(String[] args) throws ParseException {
		int hours = 24;
		double num = Math.ceil(hours / 24.0 * 2)/2;
		System.out.println(new BigDecimal(num));
		//		System.out.println(formatYYYYMM(getNextMonth(now)));
//		System.out.println(formatYYYYMMDDHHMMSS(minusMinutes(new Date(), 10, new Date(System.currentTimeMillis() - 5*60*1000))));
//		System.out.println(getIntervalSeconds(new Date(now.getTime() - 100000), now));
//		System.out.println(getIntervalMinutes(new Date(now.getTime() - 100000), now));
//		System.out.println(MINUTE);
//		System.out.println(getIntervalDesc(100000L));
//		System.out.println(formatYYYYMMDDHHMMSS(DateTimeUtility.getTodayTime(
//				DateTimeUtility.parseHHMM("10:10")).getTime()));
//		System.out.println(formatYYYYMMDDHHMMSS(getMaxTimeOfDay(new Date())));
//		System.out.println(getDate("2014-11-11 00:00:00"));
//		System.out.println(getDailyVersion(new Date()));
//		System.out.println(getTomorrowOfWeek());
//		
//		System.out.println(isWeekendDay());
//		
//		Date n = new Date();
//		n = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(n));
//		System.out.println(n);
//		n = DateUtils.addDays(n, 1);
//		Date d = getDayTime(n, -7);
//		Date currentDate = getMinTimeOfYesterday();
//
//		System.out.println(currentDate);
//		System.out.println(n);
//		System.out.println(formatYYYYMMDDHHMMSS(n) + ", " + formatYYYYMMDDHHMMSS(d));
//		
//		System.out.println(formatYYYYMMDDHHMMSS(getMondayOfNextWeek()));
//		
//		System.out.println(isTomorrowAvailableDay("YYYNYYY"));
//		System.out.println(formatYYYYMMDDHHMMSS(DateTimeUtility.getTodayTime(
//				DateTimeUtility.parseHHMMSS("10:10:28")).getTime()));
//		System.out.println(DateTimeUtility.parseHHMMSS("10:10:28"));
//		System.out.println(formatYYYYMMDDHHMMSS(minusMinutes(new Date(), 10, new Date(System.currentTimeMillis() - 5*60*1000))));
//		System.out.println(getIntervalSeconds(new Date(now.getTime() - 100000), now));
//		System.out.println(getIntervalMinutes(new Date(now.getTime() - 100000), now));
//		System.out.println(MINUTE);
//		System.out.println(getIntervalDesc(100000L));
//		System.out.println(formatYYYYMMDDHHMMSS(DateTimeUtility.getTodayTime(
//				DateTimeUtility.parseHHMM("10:10")).getTime()));
//		System.out.println(formatYYYYMMDDHHMMSS(getMaxTimeOfDay(new Date())));
//		System.out.println(getDate("2014-11-11 00:00:00"));
//		System.out.println(getDailyVersion(new Date()));
//		System.out.println(getTomorrowOfWeek());
//		
//		System.out.println(isWeekendDay());
//		
//		Date n = new Date();
//		n = DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(n));
//		System.out.println(n);
//		n = DateUtils.addDays(n, 1);
//		Date d = getDayTime(n, -7);
//		Date currentDate = getMinTimeOfYesterday();
//
//		System.out.println(currentDate);
//		System.out.println(n);
//		System.out.println(formatYYYYMMDDHHMMSS(n) + ", " + formatYYYYMMDDHHMMSS(d));
//		
//		System.out.println(formatYYYYMMDDHHMMSS(getMondayOfNextWeek()));
//		
//		System.out.println(isTomorrowAvailableDay("YYYNYYY"));
//		System.out.println(formatYYYYMMDDHHMMSS(DateTimeUtility.getTodayTime(
//				DateTimeUtility.parseHHMMSS("10:10:28")).getTime()));
//		System.out.println(DateTimeUtility.parseHHMMSS("10:10:28"));
//		*/
	}
}
