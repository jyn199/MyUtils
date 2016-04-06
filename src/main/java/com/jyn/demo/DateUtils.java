package com.jyn.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {
	public static String getIsDSTOrUTC() {
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");

		Calendar start = Calendar.getInstance(timeZone);
		Calendar nowCalendar = Calendar.getInstance(timeZone);
		start.setTime(new Date(0));// UTC 1970-01-01

		long now = Calendar.getInstance(timeZone).getTimeInMillis();
		long year = 1000l * 3600 * 24 * 365;
		long end = now + year * 5;//

		List<Calendar> dateList = new ArrayList<Calendar>();
		
		for (long i = start.getTimeInMillis(); i < end; i = start.getTimeInMillis()) {
			start.add(Calendar.DATE, 1); // add one day

			if ((start.getTimeInMillis() - i) % (24 * 3600 * 1000L) != 0) {
				Date date = new Date(i);
				Calendar calendar = Calendar.getInstance(timeZone);
				calendar.setTime(date);
				
				if(nowCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
					dateList.add(calendar);
				}
			}
		}
		
		int nowMonth = nowCalendar.get(Calendar.MONTH) + 1;
		
		if(nowMonth < dateList.get(0).get(Calendar.MONTH) + 1) {
			return "UTC";
		}else if(dateList.get(0).get(Calendar.MONTH) + 1 < nowMonth && nowMonth < dateList.get(1).get(Calendar.MONTH) + 1) {
			return "DST";
		}else {
			return "UTC";
		}
	}
	
	public static String getAmericanEastDateTime() {
		TimeZone tz = TimeZone.getTimeZone("America/New_York");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(tz);
		
		Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(tz);
		return formatter.format(calendar.getTime());
	}
}
