package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.TimeRangeVO;

public class HalfDate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1085400883871643261L;
	private String date;// 日期
	private Boolean am; // 是否上午

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getAm() {
		return am;
	}

	public void setAm(Boolean am) {
		this.am = am;
	}
	
	public static List<TimeRangeVO> toTimeRangeVOList (List<HalfDate> originList) throws ParseException {
		List<TimeRangeVO> list = new ArrayList<TimeRangeVO>();
		if (originList != null && originList.size() > 0) {
			for (HalfDate halfDate : originList) {
				Date date = DateTimeUtility.parseYYYYMMDD(halfDate.getDate());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				Date begin = new Date();
				Date end = new Date();
				if (halfDate.getAm() == null || halfDate.getAm()) {
					begin = calendar.getTime();
					calendar.add(Calendar.HOUR_OF_DAY, 12);
					end = calendar.getTime();
				} else {
					calendar.add(Calendar.HOUR_OF_DAY, 12);
					begin = calendar.getTime();
					calendar.add(Calendar.HOUR_OF_DAY, 12);
					end = calendar.getTime();
				}
				TimeRangeVO timeRangeVO = new TimeRangeVO();
				timeRangeVO.setFrom(begin);
				timeRangeVO.setTo(end);
				
				list.add(timeRangeVO);
			}
		}
		return list;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof HalfDate 
				&& this.date != null
				&& this.date.equals(((HalfDate) obj).getDate())
				&& this.am == ((HalfDate) obj).getAm()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.date.length();
	}
	
	public static void main(String[] args) {
		HalfDate hd1 = new HalfDate();
		hd1.setAm(true);
		hd1.setDate("123");
		HalfDate hd2 = new HalfDate();
		hd2.setAm(true);
		hd2.setDate("123");
		
		System.out.println(hd1.equals(hd2));
	}
}
