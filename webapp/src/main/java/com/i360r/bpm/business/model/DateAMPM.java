package com.i360r.bpm.business.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.i360r.framework.util.DateTimeUtility;

public class DateAMPM {

	private Date date; 
	private boolean am;
	private boolean from;
	
	private Date dateHour;
	
	public DateAMPM(Date date, boolean am, boolean from) {
		this.date = date;
		this.am = am;
		this.from = from;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		if (!am) {
			calendar.add(Calendar.HOUR_OF_DAY, 12);
		}
		if (!from) {
			calendar.add(Calendar.HOUR_OF_DAY, 12);
		}
		this.dateHour = calendar.getTime();
	}
	
	public DateAMPM(Date dateHour, boolean from) {
		this.dateHour = dateHour;
		this.from = from;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateHour);
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		if (from && hour == 0) {
			this.date = toDate(dateHour);
			this.am = true;
		}
		
		if (from && hour == 12) {
			this.date = toDate(dateHour);
			this.am = false;
		}
		
		if (!from && hour == 0) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			this.date = toDate(dateHour);
			this.am = false;
		}
		if (!from && hour == 12) {
			this.date = toDate(dateHour);
			this.am = true;
		}		
	}

	public Date getDate() {
		return date;
	}

	public boolean isAm() {
		return am;
	}

	public boolean isFrom() {
		return from;
	}

	public Date getDateHour() {
		return dateHour;
	}
	
	private Date toDate(Date dateTime) {
		try {
			return dateTime == null ? null : DateTimeUtility.parseYYYYMMDD(DateTimeUtility.formatYYYYMMDD(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static void main(String[] args) throws ParseException {
		DateAMPM a1 = new DateAMPM(DateTimeUtility.parseYYYYMMDD("2015-06-07"), true, true);
		System.out.println(DateTimeUtility.formatYYYYMMDDHHMMSS(a1.getDateHour()));
		
		DateAMPM a2 = new DateAMPM(DateTimeUtility.parseYYYYMMDD("2015-06-07"), false, true);
		System.out.println(DateTimeUtility.formatYYYYMMDDHHMMSS(a2.getDateHour()));
		
		DateAMPM a3 = new DateAMPM(DateTimeUtility.parseYYYYMMDD("2015-06-07"), true, false);
		System.out.println(DateTimeUtility.formatYYYYMMDDHHMMSS(a3.getDateHour()));
		
		DateAMPM a4 = new DateAMPM(DateTimeUtility.parseYYYYMMDD("2015-06-07"), false, false);
		System.out.println(DateTimeUtility.formatYYYYMMDDHHMMSS(a4.getDateHour()));
		
	}
}
